import SwiftUI
import Shared

struct MainView: View {
    
    @StateObject private var viewModelHolder = ViewModelHolder<MainViewModel>()
    
    var body: some View {
        Observing(viewModelHolder.instance.uiState) { uiState in
            switch onEnum(of: uiState) {
            case .loading:
                LoadingView()
            case .error:
                ErrorView()
            case .success(let result):
                SuccessView(
                    activeTasks: result.activeTasks,
                    completedTasks: result.completedTasks,
                    onCompleteTaskClick: { taskId, isCompleted in
                        viewModelHolder.instance.setIsCompleted(taskId: taskId, isCompleted:isCompleted)
                    }
                )
            }
        }
    }
}

private enum Page {
    case active
    case completed
}

private struct SuccessView: View {
    
    let activeTasks: [Task]
    let completedTasks: [Task]
    let onCompleteTaskClick: (Int64, Bool) -> Void
    
    @State private var selectedPage = Page.active
    @State private var navigationPath = NavigationPath()
    @State private var showAddTaskSheet = false
    
    var body: some View {
        NavigationStack(path: $navigationPath) {
            VStack {
                Picker(selection: $selectedPage, label: Text("Select a page")) {
                    Text("Active").tag(Page.active)
                    Text("Completed").tag(Page.completed)
                }
                .pickerStyle(.segmented)
                
                switch selectedPage {
                case Page.active:
                    List(activeTasks, id: \.id) { task in
                        listItem(task: task)
                    }
                case Page.completed:
                    List(completedTasks, id: \.id) { task in
                        listItem(task: task)
                    }
                }
            }
            .toolbar {
                ToolbarItem(placement: .navigationBarTrailing) {
                    Button(action: {
                        showAddTaskSheet.toggle()
                    }, label: {
                        Image(systemName: "plus")
                    })
                }
                //
                //                ToolbarItem(placement: .navigationBarTrailing) {
                //                    Button(action: {
                //                        navigationPath.append(Route.settings)
                //                    }, label: {
                //                        Image(systemName: "gear")
                //                    })
                //                }
                
            }
            .navigationTitle("Todo List").navigationBarTitleDisplayMode(.inline)
            .navigationDestination(for: Route.self) { route in
                switch route {
                case let .taskDetails(taskId):
                    TaskDetailsView(taskId: taskId, navigationPath: $navigationPath)
                case .settings:
                    Text("Settings") // TODO
                }
            }
            .sheet(isPresented: $showAddTaskSheet) {
                AddTaskView()
            }
        }
    }
    
    @ViewBuilder private func listItem(task: Task) -> some View {
        NavigationLink(value: Route.taskDetails(task.id)) {
            TaskRow(task: task, onCompleteTaskClick: { taskId, isCompleted in
                self.onCompleteTaskClick(taskId, isCompleted)
            })
        }
    }
}

struct MainView_Previews: PreviewProvider {
    static var previews: some View {
        MainView()
    }
}
