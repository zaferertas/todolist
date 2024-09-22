import SwiftUI
import Shared


struct AddTaskView: View {
    
    @StateObject private var viewModelHolder = ViewModelHolder<AddTaskViewModel>()
    
    @State private var title: String = ""
    @State private var description: String? = ""
    
    @Environment(\.dismiss) private var dismiss
    
    @FocusState var isFocused: Bool
    
    var body: some View {
        
        NavigationStack {
            Form {
                Section {
                    TextField("Title", text: $title)
                        .focused($isFocused)
                        .onAppear {
                            self.isFocused = true
                        }
                    
                    TextField(
                        "Description",
                        text: Binding(
                            get: { description ?? "" },
                            set: { newValue in
                                description = newValue
                            }
                        )
                    )
                }
                Section {
                    Button {
                        viewModelHolder.instance.addTask(title: self.title, description: self.description)
                        dismiss()
                    } label: {
                        Text("Save")
                    }
                    .disabled(title.isEmpty)
                }
            }
            .navigationTitle("Add Task")
            .navigationBarTitleDisplayMode(.inline)
            .toolbar {
                ToolbarItem(placement: .navigationBarLeading) {
                    Button("Cancel") {
                        dismiss()
                    }
                }
            }
        }
    }
}


#Preview {
    return AddTaskView()
}
