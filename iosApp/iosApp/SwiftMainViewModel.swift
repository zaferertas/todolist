import Foundation
import Shared

class SwiftMainViewModel: ObservableObject {
    
//    let viewModel = TasksViewModel()
    private let viewModel = KotlinDependencies.shared.getMainViewModel()

    @Published
    private(set) var tasks: [Task] = []

    @MainActor
    func activate() async {
//        for await uiState in viewModel.uiState {
//            self.tasks = tasks
//        }

        for try await uiState in viewModel.uiState {
            switch onEnum(of: uiState) {
                case .error:
                    showError()
                case .success(let result):
                    self.tasks = result.activeTasks
                case .loading:
                    showLoading()
            }
        }

    }

    func showLoading() {
        print("Loading...")
    }

    func showError() {
        print("Error")
    }

    func addTask(title: String, description: String?) {
//        viewModel.addTask(title: title, description: description)
    }
}
