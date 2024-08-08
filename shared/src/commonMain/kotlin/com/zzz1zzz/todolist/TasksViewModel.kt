package com.zzz1zzz.todolist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.stateIn

class TasksViewModel(
    private val tasksRepository: TasksRepository
) : ViewModel() {

    val tasks: StateFlow<List<Task>> = flowOf(tasksRepository.getTasks())
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun addTask(name: String, description: String?) {
        tasksRepository.addTask(name, description)
    }
}
