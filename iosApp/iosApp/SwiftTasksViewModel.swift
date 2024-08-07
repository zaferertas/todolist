import Foundation
import Shared

class SwiftTasksViewModel: ObservableObject {
    
    let viewModel = TasksViewModel()

    @Published
    private(set) var tasks: [Task] = []

    @MainActor
    func activate() async {
        for await tasks in viewModel.tasks {
            self.tasks = tasks
        }
    }
}
