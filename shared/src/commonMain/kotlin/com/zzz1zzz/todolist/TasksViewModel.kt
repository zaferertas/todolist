package com.zzz1zzz.todolist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.stateIn

class TasksViewModel : ViewModel() {

    val tasks: StateFlow<List<Task>> = flowOf(dummyTasksList)
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun addTask(name: String, description: String?) {

    }
}

val dummyTasksList = listOf(
    Task(
        id = 0,
        title = "Task 1",
        description = null,
        isCompleted = false,
    ),
    Task(
        id = 1,
        title = "Task 2",
        description = null,
        isCompleted = false,
    ),
    Task(
        id = 2,
        title = "Task 3",
        description = null,
        isCompleted = true,
    ),
    Task(
        id = 3,
        title = "Task 4",
        description = null,
        isCompleted = false,
    )
)