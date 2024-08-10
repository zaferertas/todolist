import SwiftUI
import Shared

struct ContentView: View {
    @State private var showContent = false
    
    @StateObject var viewModel = SwiftTasksViewModel()
    
    var body: some View {
        
        VStack(alignment: .center) {
            Text("Todo List")
            List(viewModel.tasks, id: \.id) { task in
                Text(task.title)
            }
            Button("Save") {
                viewModel.addTask(title: "title test", description: "description test")
            }
        }.task {
            await viewModel.activate()
        }
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
