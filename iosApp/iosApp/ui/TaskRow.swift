import SwiftUI
import Shared

struct TaskRow: View {
    
    let task: Task
    let onCompleteTaskClick: (Int64, Bool) -> Void
    
    var body: some View {
        HStack{
            Button {
                onCompleteTaskClick(task.id, !task.isCompleted)
            } label: {
                Image(systemName: task.isCompleted ? "checkmark.circle.fill" : "circle")
            }
            .buttonStyle(.plain)
            
            Text(task.title)
        }
        .padding()
        .frame(maxWidth: .infinity, alignment: .leading)
        .swipeActions {
            Button(task.isCompleted ? "Uncomplete" : "Complete") {
                onCompleteTaskClick(task.id, !task.isCompleted)
            }
            .tint(task.isCompleted ? .gray : .green)
        }
    }
}

#Preview {
    TaskRow(task: Task(
        id: 0, title: "Title", description: "Description", isCompleted: false, createdAt: "", completedAt: nil),
            onCompleteTaskClick: { _, _  in  }
    )
}
