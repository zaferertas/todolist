package com.zzz1zzz.todolist.domain

import kotlinx.coroutines.flow.Flow

interface TaskRepository {
    fun getTasks() : Flow<List<Task>>
    suspend fun addTask(title: String, description: String?)
}