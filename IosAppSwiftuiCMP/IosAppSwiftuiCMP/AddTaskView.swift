import SwiftUI
import Sharedui

struct AddTaskView: View {
    
    @Binding var sheetShowing: Bool
    
    var body: some View {
        NavigationStack {
            AddTaskViewCompose(
                sheetShowing: $sheetShowing
            )
            .navigationTitle("Add Task")
            .navigationBarTitleDisplayMode(.inline)
            .toolbar {
                ToolbarItem(placement: .navigationBarLeading) {
                    Button("Cancel") {
                        sheetShowing = false
                    }
                }
            }
        }
    }
}

private struct AddTaskViewCompose: UIViewControllerRepresentable {
    
    @Binding var sheetShowing: Bool
    
    func makeUIViewController(context: Context) -> UIViewController {
        AddTaskViewControllerKt.AddTaskViewController(
            navigateBack: {
                sheetShowing = false
            }
        )
    }
    func updateUIViewController(_ uiViewController: UIViewController, context: Context) {}
}
