package com.zzz1zzz.todolist.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.touchlab.kermit.Logger
import com.zzz1zzz.todolist.domain.TaskRepository
import com.zzz1zzz.todolist.viewModel.TaskDetailsViewModel.Companion
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class AddTaskViewModel : ViewModel(), KoinComponent {

    private val taskRepository: TaskRepository by inject()

    fun addTask(title: String, description: String?) {
        Logger.d(TAG) {"addTask, title: $title, description: $description"}
        viewModelScope.launch {
            taskRepository.addTask(title, description)
        }
    }

    companion object {
        private const val TAG = "AddTaskViewModel"
    }
}
