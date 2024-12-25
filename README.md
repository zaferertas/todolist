[![Kotlin](https://img.shields.io/badge/Kotlin-2.0.20-blue.svg?style=flat&logo=kotlin)](https://kotlinlang.org)

![badge-android](http://img.shields.io/badge/platform-android-6EDB8D.svg?style=flat)
![badge-desktop](http://img.shields.io/badge/platform-ios-EAEAEA.svg?style=flat)

## General Info

A Kotlin Multiplatform demo project targeting Android and iOS.

The business logic (data, domain and viewmodels) is written in Kotlin and shared across all platforms. The UI components are built using Compose Multiplatform and SwiftUI.

 To demonstrate different UI sharing strategies, 3 apps have been developed for the IOS platform. 

| App              | UI                                                                    | 
|------------------|-----------------------------------------------------------------------|
| androidApp       | Compose Multiplatform                                                 |
| iosApp           | SwiftUI                                                               |
| iosAppCMP        | Compose Multiplatform                                                 |
| iosAppSwiftuiCMP | Hybrid: Navigation and controls with SwiftUi, Screen Content with CMP |

## Run project
### Android
To run the application on android device/emulator:
- open project in Android Studio and run imported android run configuration

### iOS
To run the application on iPhone device/simulator:
- Open `ios/iosApp.xcworkspace` in Xcode and run standard configuration
- Or use [Kotlin Multiplatform Mobile plugin](https://plugins.jetbrains.com/plugin/14936-kotlin-multiplatform-mobile) for Android Studio

## Screenshots
### Android
<img src="art/Screenshot android.png"  width="250" alt="Android"/>

### iOS with SwiftUI
<img src="art/screenshot_ios_swiftui.png"  width="250" alt="IOS"/>

### iOS with CMP
<img src="art/screenshot_ios_cmp.png"  width="250" alt="IOS"/>

### iOS with SwiftUI and CMP
<img src="art/screenshot_ios_swiftui_cmp.png"  width="250" alt="IOS"/>

## Libraries used
- 🧩 [Jetpack Compose](https://developer.android.com/compose); for the Android UI
- 🧩 [SwiftUI](https://developer.apple.com/xcode/swiftui/); for the IOS UI
- 🔶 [Skie](https://skie.touchlab.co) - Swift-friendly API Generator for Kotlin Multiplatform
- 🔷 [kotlinx.coroutines](https://github.com/Kotlin/kotlinx.coroutines) - Library support for Kotlin coroutines with multiplatform support
- 📦 [Kotlinx Serialization](https://github.com/Kotlin/kotlinx.serialization); for content negotiation
- 🕰️ [Kotlinx Datetime](https://github.com/Kotlin/kotlinx-datetime); for datetime
- 🗄 [SQLDelight](https://github.com/cashapp/sqldelight) - for the sqlite database
- ⧉  [Koin](https://insert-koin.io/) - For dependency injection
- 🔶 [Kermit](https://kermit.touchlab.co/) - For logging

## Related Resources

- [Kotlin Multiplatform](https://kotlinlang.org/docs/multiplatform-get-started.html)
- [Get started with Kotlin Multiplatform](https://www.jetbrains.com/help/kotlin-multiplatform-dev/get-started.html)
- [Using Koin in a Kotlin Multiplatform Project](https://johnoreilly.dev/posts/kotlinmultiplatform-koin/)
- [Kotlin Multiplatform samples](https://www.jetbrains.com/help/kotlin-multiplatform-dev/multiplatform-samples.html)
