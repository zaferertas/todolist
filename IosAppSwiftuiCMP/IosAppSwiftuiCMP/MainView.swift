import UIKit
import SwiftUI
import Sharedui

struct MainView: View {
    var body: some View {
        TabView {
            TasksView()
                .tabItem {
                    Image(systemName: "list.bullet")
                    Text("Tasks")
            }
            SettingsView()
                .tabItem {
                    Image(systemName: "gear")
                    Text("Settings")
            }
        }
    }
}

#Preview {
    MainView()
}
