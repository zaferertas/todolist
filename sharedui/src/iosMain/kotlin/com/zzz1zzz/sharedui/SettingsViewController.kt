package com.zzz1zzz.sharedui

import androidx.compose.ui.window.ComposeUIViewController
import com.zzz1zzz.sharedui.ui.settings.SettingsScreen
import com.zzz1zzz.sharedui.ui.tasks.TasksScreen

fun SettingsViewController() = ComposeUIViewController {
    SettingsScreen()
}