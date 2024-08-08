import Foundation
import Shared

class SwiftTasksViewModel: ObservableObject {
    
//    let viewModel = TasksViewModel()
    let viewModel = KotlinDependencies.shared.getTasksViewModel()

    @Published
    private(set) var tasks: [Task] = []

    @MainActor
    func activate() async {
        for await tasks in viewModel.tasks {
            self.tasks = tasks
        }
    }
}
