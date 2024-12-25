import SwiftUI
import Sharedui


@main
struct iosAppCMPApp: App {

    init() {
        KoinKt_.doInitKoin()
    }

    var body: some Scene {
        WindowGroup {
            MainView()
        }
    }
}
