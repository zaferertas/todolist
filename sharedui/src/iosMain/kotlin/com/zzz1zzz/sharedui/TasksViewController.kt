package com.zzz1zzz.sharedui

import androidx.compose.ui.window.ComposeUIViewController
import com.zzz1zzz.sharedui.ui.tasks.TasksScreen

fun TasksViewController(onTaskClick: (Long) -> Unit) = ComposeUIViewController {
    TasksScreen(
        onTaskClick = onTaskClick,
    )
}