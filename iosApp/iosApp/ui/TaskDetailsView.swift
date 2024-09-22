import SwiftUI
import Shared

struct TaskDetailsView: View {
    
    @StateObject private var viewModelHolder = ViewModelHolder<TaskDetailsViewModel>()
    
    let taskId: Int64
    @Binding var navigationPath: NavigationPath
    
    @State private var isPresentingDeleteAlert: Bool = false
    
    var body: some View {
        VStack{
            Observing(viewModelHolder.instance.uiState) { uiState in
                switch onEnum(of: uiState) {
                case .error:
                    ErrorView()
                case .success(let result):
                    SuccessView(
                        task: Binding(get: { return result.task }, set: { _ in }),
                        onSaveClick: { title, description in
                            viewModelHolder.instance.updateTask(taskId: taskId, title: title, description: description)
                            navigationPath.removeLast()
                        },
                        onSetActiveClick: { isCompleted in
                            viewModelHolder.instance.setIsCompleted(taskId: taskId, isCompleted: isCompleted)
                        }
                    )
                case .loading:
                    LoadingView()
                }
            }
        }
        .navigationTitle("Task Details")
        .navigationBarTitleDisplayMode(.inline)
        .toolbar {
            ToolbarItem(placement: .navigationBarTrailing) {
                Button{
                    isPresentingDeleteAlert = true
                } label: {
                    Image(systemName:  "trash")
                }
            }
        }.task {
            viewModelHolder.instance.fetchTask(taskId: taskId)
        }
        .confirmationDialog("Are you sure?", isPresented: $isPresentingDeleteAlert) {
            Button("Delete this task?", role: .destructive) {
                viewModelHolder.instance.deleteTask(taskId: taskId)
                navigationPath.removeLast()
            }
        }
    }
    
    private struct SuccessView: View {
        @Binding var task: Task

        @State private var title: String = ""
        @State private var description: String = ""
        
        var onSaveClick: (String, String) -> Void
        var onSetActiveClick: (Bool) -> Void
        
        var body: some View {
            VStack {
                Form {
                    Section {
                        VStack(alignment: .leading) {
                            HStack {
                                Text(task.isCompleted ? "Completed" : "Active")
                                    .font(.system(.title2, design: .rounded).weight(.medium))
                                Spacer()
                                Button(task.isCompleted ? "Set as Active" : "Set Completed") {
                                    onSetActiveClick(!task.isCompleted)
                                }
                            }
                            Spacer()
                                .frame(height: 20)
                            Text("Created at: \(task.createdAt)")
                            if let completedAt = task.completedAt {
                                Text("Completed at: \(completedAt)")
                            }
                        }
                    }
                    Section {
                        TextField("Title", text: $title)
                        TextField("Description", text: $description)
                    }
                    Section {
                        Button {
                            onSaveClick(title, description)
                        } label: {
                            Text("Save")
                        }
                        .disabled(title.isEmpty)
                    }
                }
            }.onAppear {
                self.title = task.title
                self.description = task.description_ ?? ""
            }
        }
    }
}
#Preview {
    @State var navigationPath = NavigationPath()
    return TaskDetailsView(taskId: 1, navigationPath: $navigationPath)
}
