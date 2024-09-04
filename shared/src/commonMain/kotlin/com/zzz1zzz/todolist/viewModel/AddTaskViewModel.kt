package com.zzz1zzz.todolist.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zzz1zzz.todolist.domain.TaskRepository
import kotlinx.coroutines.launch

class AddTaskViewModel(
    private val taskRepository: TaskRepository,
) : ViewModel() {

    fun addTask(title: String, description: String?) {
        viewModelScope.launch {
            taskRepository.addTask(title, description)
        }
    }
}
