package com.zzz1zzz.todolist.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zzz1zzz.todolist.domain.Task
import com.zzz1zzz.todolist.domain.TaskRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class TaskDetailsViewModel(
    private val taskRepository: TaskRepository,
) : ViewModel() {

    private var _state = MutableStateFlow<TaskDetailsUiState>(TaskDetailsUiState.Loading)
    val state: StateFlow<TaskDetailsUiState>
        get() = _state

    private lateinit var taskJob: Job

    fun fetchTask(taskId: Long) {
        taskJob = viewModelScope.launch(Dispatchers.IO) {
            taskRepository.getTask(taskId).collect {
                _state.value = TaskDetailsUiState.Success(it)
            }.runCatching {
                _state.value = TaskDetailsUiState.Error
            }
        }
    }

    fun updateTask(taskId: Long, title: String, description: String?) {
        viewModelScope.launch(Dispatchers.IO) {
            taskRepository.updateTask(
                taskId = taskId,
                title = title,
                description = description,
            )
        }
    }

    fun deleteTask(taskId: Long) = viewModelScope.launch(Dispatchers.IO) {
        taskJob.cancel()
        taskRepository.deleteTask(taskId)
    }

    fun setIsCompleted(taskId: Long, isCompleted: Boolean) =
        viewModelScope.launch(Dispatchers.IO) {
            taskRepository.setCompleted(taskId, isCompleted)
        }
}

sealed class TaskDetailsUiState {
    data object Loading : TaskDetailsUiState()
    data object Error : TaskDetailsUiState()
    class Success(val task: Task) : TaskDetailsUiState()
}
