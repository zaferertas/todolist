package com.zzz1zzz.todolist.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zzz1zzz.todolist.domain.Task
import com.zzz1zzz.todolist.domain.TaskRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class MainViewModel(
    private val taskRepository: TaskRepository,
) : ViewModel() {

    val uiState: StateFlow<MainUiState> = taskRepository.getTasks()
        .map { tasks ->
            val (activeTasks, completedTasks) = tasks.partition { !it.isCompleted }
            MainUiState.Success(activeTasks, completedTasks)
        }.catch {
            MainUiState.Error
        }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            MainUiState.Loading
        )

    fun setIsCompleted(taskId: Long, isCompleted: Boolean) =
        viewModelScope.launch(Dispatchers.IO) {
            taskRepository.setCompleted(taskId, isCompleted)
        }
}

sealed class MainUiState {
    data object Loading : MainUiState()
    data object Error : MainUiState()
    class Success(
        val activeTasks: List<Task>,
        val completedTasks: List<Task>,
    ) : MainUiState()
}

