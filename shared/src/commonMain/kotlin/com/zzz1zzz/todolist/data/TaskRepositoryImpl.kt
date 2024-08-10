package com.zzz1zzz.todolist.data

import com.zzz1zzz.todolist.domain.TaskRepository
import kotlinx.coroutines.flow.map

class TaskRepositoryImpl(
    private val taskDataSource: TaskDataSource,
) : TaskRepository {

    override fun getTasks() = taskDataSource.getTasks().map {
        it.map { taskData ->
            taskData.asDomain()
        }
    }

    override suspend fun addTask(title: String, description: String?) {
        taskDataSource.addTask(title, description)
    }
}