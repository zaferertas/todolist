import SwiftUI
import Sharedui

struct TaskDetailsView: View {
    
    let taskId: Int64
    @Binding var navigationPath: NavigationPath
    @State private var isPresentingDeleteAlert: Bool = false
    
    let viewModel: TaskDetailsViewModel = TaskDetailsViewModel()
    
    var body: some View {
        TaskDetailsViewCompose(
            taskId: taskId,
            viewModel: viewModel,
            navigateBack: {
                navigationPath.removeLast(navigationPath.count)
            }
        )
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
        }
        .confirmationDialog("Are you sure?", isPresented: $isPresentingDeleteAlert) {
            Button("Delete this task?", role: .destructive) {
                viewModel.deleteTask(taskId: taskId)
                navigationPath.removeLast(navigationPath.count)
            }
        }
    }
    
}


private struct TaskDetailsViewCompose: UIViewControllerRepresentable {
    
    let taskId: Int64
    let viewModel: TaskDetailsViewModel
    let navigateBack: () -> Void
    
    func makeUIViewController(context: Context) -> UIViewController {
        TaskDetailsViewControllerKt.TaskDetailsViewController(
            taskId: taskId,
            viewModel: viewModel,
            navigateBack: {
                navigateBack()
            }
        )
    }
    func updateUIViewController(_ uiViewController: UIViewController, context: Context) {}
}
