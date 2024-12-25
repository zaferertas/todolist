package com.zzz1zzz.sharedui.ui.tasks

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.zzz1zzz.sharedui.ui.TaskDataPreviewProvider
import com.zzz1zzz.todolist.domain.Task
import com.zzz1zzz.todolist.viewModel.TasksUiState
import com.zzz1zzz.todolist.viewModel.TasksViewModel
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.jetbrains.compose.ui.tooling.preview.PreviewParameter
import org.koin.compose.viewmodel.koinViewModel


@Composable
fun TasksScreen(
    modifier: Modifier = Modifier,
    onTaskClick: (Long) -> Unit,
) {
    val viewModel: TasksViewModel = koinViewModel()
    val uiState = viewModel.uiState.collectAsState()

    Box(
        modifier = modifier.fillMaxSize()
    ) {
        when (uiState.value) {
            TasksUiState.Error -> {}
            TasksUiState.Loading -> {}
            is TasksUiState.Success -> TasksScreenContent(
                activeTasks = (uiState.value as TasksUiState.Success).activeTasks,
                completedTasks = (uiState.value as TasksUiState.Success).completedTasks,
                onItemClick = onTaskClick,
                onCompleteTaskClick = viewModel::setIsCompleted,
            )
        }
    }
}

@Composable
private fun TasksScreenContent(
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
        TasksTabs(
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