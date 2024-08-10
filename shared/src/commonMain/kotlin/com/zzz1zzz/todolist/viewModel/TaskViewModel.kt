package com.zzz1zzz.todolist.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zzz1zzz.todolist.domain.Task
import com.zzz1zzz.todolist.domain.TaskRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class TaskViewModel(
    private val taskRepository: TaskRepository,
) : ViewModel() {

    val tasks: StateFlow<List<Task>> = taskRepository.getTasks()
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            emptyList()
        )

    fun addTask(title: String, description: String?) {
        viewModelScope.launch {
            taskRepository.addTask(title, description)
        }
    }
}
