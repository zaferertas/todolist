package com.zzz1zzz.todolist.ui.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.zzz1zzz.todolist.domain.Task
import com.zzz1zzz.todolist.ui.TaskDataPreviewProvider
import com.zzz1zzz.todolist.viewModel.MainUiState
import com.zzz1zzz.todolist.viewModel.MainViewModel
import org.koin.androidx.compose.koinViewModel


@Composable
fun MainRoute(
    modifier: Modifier = Modifier,
    onAddTaskClick: () -> Unit = {},
    onTaskClick: (Long) -> Unit = {},
) {
    val viewModel: MainViewModel = koinViewModel()
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()

    MainScreen(
        modifier = modifier,
        uiState = uiState.value,
        onAddTaskClick = onAddTaskClick,
        onTaskClick = onTaskClick,
        onCompleteTaskClick = { taskId, isCompleted ->
            viewModel.setIsCompleted(taskId, isCompleted)
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun MainScreen(
    modifier: Modifier = Modifier,
    uiState: MainUiState,
    onAddTaskClick: () -> Unit,
    onTaskClick: (Long) -> Unit,
    onCompleteTaskClick: (Long, Boolean) -> Unit,
) {

    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text("Todo List")
                },
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    onAddTaskClick()
                }
            ) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = "Add task" // TODO get from resources
                )
            }
        }
    ) { contentPadding ->
        Box(
            modifier = Modifier.padding(contentPadding)
        ) {
            when (uiState) {
                MainUiState.Error -> {}
                MainUiState.Loading -> {}
                is MainUiState.Success -> SuccessView(
                    activeTasks = uiState.activeTasks,
                    completedTasks = uiState.completedTasks,
                    onItemClick = onTaskClick,
                    onCompleteTaskClick = onCompleteTaskClick,
                )
            }
        }
    }
}

@Composable
private fun SuccessView(
    activeTasks: List<Task>,
    completedTasks: List<Task>,
    onItemClick: (Long) -> Unit,
    onCompleteTaskClick: (Long, Boolean) -> Unit,
) {

    val pagerState = rememberPagerState(
        initialPage = 0,
        initialPageOffsetFraction = 0f,
        pageCount = { 2 }
    )

    Column {
        MainTabs(
            pagerState = pagerState,
        )

        HorizontalPager(
            state = pagerState
        ) { page ->
            when (page) {
                0 -> ItemView(
                    tasks = activeTasks,
                    onItemClick = onItemClick,
                    onCompleteTaskClick = onCompleteTaskClick
                )

                1 -> ItemView(
                    tasks = completedTasks,
                    onItemClick = onItemClick,
                    onCompleteTaskClick = onCompleteTaskClick
                )
            }
        }
    }
}

@Composable
fun ItemView(
    modifier: Modifier = Modifier,
    tasks: List<Task>,
    onItemClick: (Long) -> Unit,
    onCompleteTaskClick: (Long, Boolean) -> Unit,
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
    ) {
        items(tasks) { task ->
            TaskListItem(
                modifier = Modifier.animateItem(),
                task = task,
                onItemClick = { itemId ->
                    onItemClick(itemId)
                },
                onCompleteTaskClick = onCompleteTaskClick
            )
        }
    }
}

@Preview
@Composable
private fun MainScreenPreview(@PreviewParameter(TaskDataPreviewProvider::class) tasks: List<Task>) {
    MainScreen(
        uiState = MainUiState.Success(
            tasks,
            listOf()
        ),
        onTaskClick = {},
        onAddTaskClick = {},
        onCompleteTaskClick = { _, _ -> }
    )
}
