package com.zzz1zzz.sharedui

import androidx.compose.ui.window.ComposeUIViewController
import com.zzz1zzz.sharedui.ui.tasks.TasksScreen
import com.zzz1zzz.sharedui.ui.addtask.AddTaskScreen

fun AddTaskViewController(navigateBack: () -> Unit) = ComposeUIViewController {
    AddTaskScreen(
        navigateBack = navigateBack,
    )
}