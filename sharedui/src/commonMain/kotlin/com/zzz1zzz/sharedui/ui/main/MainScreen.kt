package com.zzz1zzz.sharedui.ui.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.zzz1zzz.sharedui.ui.TaskDataPreviewProvider
import com.zzz1zzz.sharedui.ui.settings.SettingsScreen
import com.zzz1zzz.sharedui.ui.tasks.TasksScreen
import com.zzz1zzz.todolist.domain.Task
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.jetbrains.compose.ui.tooling.preview.PreviewParameter
import todolist.sharedui.generated.resources.Res
import todolist.sharedui.generated.resources.app_name
import todolist.sharedui.generated.resources.screen_name_add_task


@Composable
fun MainRoute(
    modifier: Modifier = Modifier,
    onAddTaskClick: () -> Unit = {},
    onTaskClick: (Long) -> Unit = {},
) {
    var selectedScreenId by remember { mutableIntStateOf(0) }

    MainScreen(
        modifier = modifier,
        selectedScreenId = selectedScreenId,
        onAddTaskClick = onAddTaskClick,
        onTaskClick = onTaskClick,
        onBottomBarBarItemClick = { screenId ->
            selectedScreenId = screenId
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun MainScreen(
    modifier: Modifier = Modifier,
    selectedScreenId: Int,
    onAddTaskClick: () -> Unit = {},
    onTaskClick: (Long) -> Unit = {},
    onBottomBarBarItemClick: (Int) -> Unit,
) {

    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(Res.string.app_name))
                },
            )
        },
        bottomBar = {
            BottomNavigationBar(
                selectedPageId = selectedScreenId,
                onPageSelected = {
                    onBottomBarBarItemClick(it)
                }
            )
        },
        floatingActionButton = {
            if (selectedScreenId == BottomNavigationItems.TASKS.itemId) {
                FloatingActionButton(
                    onClick = {
                        onAddTaskClick()
                    }
                ) {
                    Icon(
                        imageVector = Icons.Filled.Add,
                        contentDescription = stringResource(Res.string.screen_name_add_task)
                    )
                }
            }
        }
    ) { innerPadding ->
        Column(
            modifier = modifier.padding(innerPadding)
        ) {
            when (selectedScreenId) {
                BottomNavigationItems.TASKS.itemId -> TasksScreen(
                    onTaskClick = onTaskClick,
                )

                BottomNavigationItems.SETTINGS.itemId -> SettingsScreen()
            }
        }
    }
}

@Preview
@Composable
private fun MainScreenPreview(@PreviewParameter(TaskDataPreviewProvider::class) tasks: List<Task>) {
//    MainScreen(
//        uiState = MainUiState.Success(
//            tasks,
//            listOf()
//        ),
//        onTaskClick = {},
//        onAddTaskClick = {},
//        onCompleteTaskClick = { _, _ -> }
//    )
}
