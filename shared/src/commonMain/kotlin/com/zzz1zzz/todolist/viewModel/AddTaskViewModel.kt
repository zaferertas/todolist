package com.zzz1zzz.todolist.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zzz1zzz.todolist.domain.TaskRepository
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class AddTaskViewModel : ViewModel(), KoinComponent {

    private val taskRepository: TaskRepository by inject()

    fun addTask(title: String, description: String?) {
        viewModelScope.launch {
            taskRepository.addTask(title, description)
        }
    }
}
