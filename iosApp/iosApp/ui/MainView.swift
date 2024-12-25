//
//  MainView.swift
//  iosApp
//
//  Created by Zafer Ertas on 23/12/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI

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
