package com.zzz1zzz.sharedui

import androidx.compose.ui.window.ComposeUIViewController
import com.zzz1zzz.sharedui.ui.taskdetails.TaskDetailsScreen
import com.zzz1zzz.todolist.viewModel.TaskDetailsViewModel

fun TaskDetailsViewController(taskId: Long, viewModel: TaskDetailsViewModel, navigateBack: () -> Unit) = ComposeUIViewController {
    TaskDetailsScreen(
        taskId = taskId,
        viewModel = viewModel,
        navigateBack = navigateBack,
    )
}