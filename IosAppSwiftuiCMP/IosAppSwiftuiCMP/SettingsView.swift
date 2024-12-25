import SwiftUI
import Sharedui

struct SettingsView: View {
    var body: some View {
        NavigationStack {
            SettingsViewCompose()
                .navigationTitle("Settings").navigationBarTitleDisplayMode(.inline)
        }
    }
}

private struct SettingsViewCompose: UIViewControllerRepresentable {
    func makeUIViewController(context: Context) -> UIViewController {
        SettingsViewControllerKt.SettingsViewController()
    }
    
    func updateUIViewController(_ uiViewController: UIViewController, context: Context) {}
}


#Preview {
    SettingsView()
}
