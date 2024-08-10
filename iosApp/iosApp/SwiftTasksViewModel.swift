import Foundation
import Shared

class SwiftTasksViewModel: ObservableObject {
    
//    let viewModel = TasksViewModel()
    private let viewModel = KotlinDependencies.shared.getTaskViewModel()

    @Published
    private(set) var tasks: [Task] = []

    @MainActor
    func activate() async {
        for await tasks in viewModel.tasks {
            self.tasks = tasks
        }
    }
    
    func addTask(title: String, description: String?) {
        viewModel.addTask(title: title, description: description)
    }
}
