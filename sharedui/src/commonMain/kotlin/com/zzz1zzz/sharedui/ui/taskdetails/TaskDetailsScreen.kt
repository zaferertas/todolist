package com.zzz1zzz.sharedui.ui.taskdetails

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import com.zzz1zzz.sharedui.ui.TaskAddEditForm
import com.zzz1zzz.todolist.domain.Task
import com.zzz1zzz.todolist.viewModel.TaskDetailsUiState
import com.zzz1zzz.todolist.viewModel.TaskDetailsViewModel
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import todolist.sharedui.generated.resources.Res
import todolist.sharedui.generated.resources.ic_delete_filled
import todolist.sharedui.generated.resources.label_active
import todolist.sharedui.generated.resources.label_back
import todolist.sharedui.generated.resources.label_cancel
import todolist.sharedui.generated.resources.label_completed
import todolist.sharedui.generated.resources.label_delete
import todolist.sharedui.generated.resources.label_set_as_active
import todolist.sharedui.generated.resources.label_set_as_completed
import todolist.sharedui.generated.resources.screen_name_task_details
import todolist.sharedui.generated.resources.text_completed_at
import todolist.sharedui.generated.resources.text_created_at
import todolist.sharedui.generated.resources.text_delete_task
import todolist.sharedui.generated.resources.title_delete_task


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskDetailsRoute(
    modifier: Modifier = Modifier,
    taskId: Long,
    navigateBack: () -> Unit = {},
) {
    val viewModel: TaskDetailsViewModel = koinViewModel()
    var openDeleteDialog by remember { mutableStateOf(false) }

    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(stringResource(Res.string.screen_name_task_details))
                },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            navigateBack()
                        }
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(Res.string.label_back),
                        )
                    }
                },
                actions = {
                    IconButton(
                        onClick = {
                            openDeleteDialog = true
                        }
                    ) {
                        Icon(
                            painterResource(Res.drawable.ic_delete_filled),
                            contentDescription = stringResource(Res.string.label_delete)
                        )
                    }
                }
            )
        },
    ) { contentPadding ->

        TaskDetailsScreen(
            modifier = Modifier.padding(contentPadding),
            taskId = taskId,
            viewModel = viewModel,
            navigateBack = navigateBack,
        )

        if (openDeleteDialog) {
            AlertDialog(
                onDismissRequest = {
                    openDeleteDialog = false
                },
                title = { Text(text = stringResource(Res.string.title_delete_task)) },
                text = { Text(text = stringResource(Res.string.text_delete_task)) },
                confirmButton = {
                    TextButton(
                        onClick = {
                            openDeleteDialog = false
                            viewModel.deleteTask(taskId)
                            navigateBack()
                        }
                    ) {
                        Text(
                            text = stringResource(Res.string.label_delete)
                        )
                    }
                },
                dismissButton = {
                    TextButton(
                        onClick = {
                            openDeleteDialog = false
                        }
                    ) {
                        Text(
                            text = stringResource(Res.string.label_cancel)
                        )
                    }
                }
            )
        }
    }
}

@Composable
fun TaskDetailsScreen(
    modifier: Modifier = Modifier,
    taskId: Long,
    viewModel: TaskDetailsViewModel,
    navigateBack: () -> Unit,
) {
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(taskId) {
        viewModel.fetchTask(taskId)
    }

    when (uiState) {
        TaskDetailsUiState.Error -> {}
        TaskDetailsUiState.Loading -> {}
        is TaskDetailsUiState.Success -> TaskDetailsContent(
            modifier = modifier,
            task = (uiState as TaskDetailsUiState.Success).task,
            onSaveClick = { title, description ->
                viewModel.updateTask(taskId, title, description)
                navigateBack()
            },
            onSetCompletedClick = {
                viewModel.setIsCompleted(taskId, it)
            }
        )
    }


}

@Composable
fun TaskDetailsContent(
    task: Task,
    modifier: Modifier = Modifier,
    onSaveClick: (String, String?) -> Unit = { _, _ -> },
    onSetCompletedClick: (Boolean) -> Unit,
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    Column(
        modifier = modifier.padding(8.dp),
    ) {
        var title by remember { mutableStateOf(task.title) }
        var description by remember { mutableStateOf(task.description) }

        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = if (task.isCompleted) stringResource(Res.string.label_completed) else stringResource(
                    Res.string.label_active
                ),
                style = MaterialTheme.typography.titleMedium,
            )
            Spacer(modifier = Modifier.weight(1f))
            TextButton(
                onClick = {
                    onSetCompletedClick(!task.isCompleted)
                }
            ) {
                Text(
                    text = if (task.isCompleted) stringResource(Res.string.label_set_as_active) else stringResource(
                        Res.string.label_set_as_completed
                    ),
                )
            }
        }

        Text(
            text = stringResource(Res.string.text_created_at, task.createdAt),
            style = MaterialTheme.typography.bodySmall,
        )
        task.completedAt?.let { completedAt ->
            Text(
                text = stringResource(Res.string.text_completed_at, completedAt),
                style = MaterialTheme.typography.bodySmall,
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        TaskAddEditForm(
            title = title,
            onTitleChanged = { title = it },
            description = description,
            onDescriptionChanged = { description = it },
            onSaveClick = {
                keyboardController?.hide()
                onSaveClick(
                    title,
                    description,
                )
            },
        )
    }
}