import SwiftUI
import Sharedui

struct TasksView: View {
    
    @State private var navigationPath = NavigationPath()
    @State private var showAddTaskSheet = false
    
    var body: some View {
        NavigationStack(path: $navigationPath) {
            TasksViewCompose(navigationPath: $navigationPath)
                .toolbar {
                    ToolbarItem(placement: .navigationBarTrailing) {
                        Button(action: {
                            showAddTaskSheet.toggle()
                        }, label: {
                            Image(systemName: "plus")
                        })
                    }
                }
            
                .navigationTitle("Tasks").navigationBarTitleDisplayMode(.inline)
                .navigationDestination(for: Int64.self) { clickedTaskId in
                    TaskDetailsView(taskId: clickedTaskId, navigationPath: $navigationPath)
                }
                .sheet(isPresented: $showAddTaskSheet) {
                    AddTaskView(sheetShowing: $showAddTaskSheet)
                }
            
        }
    }
}


private struct TasksViewCompose: UIViewControllerRepresentable {
    
    @Binding var navigationPath: NavigationPath
    
    func makeUIViewController(context: Context) -> UIViewController {
        TasksViewControllerKt.TasksViewController(
            onTaskClick: { taskId in
                navigationPath.append(taskId as! Int64)
            }
        )
    }
    
    func updateUIViewController(_ uiViewController: UIViewController, context: Context) {}
}

