package com.zzz1zzz.todolist.data

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import com.zzz1zzz.todolist.AppDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO

class TaskDataSource(
    private val appDatabase: AppDatabase
) {

    fun getTasks() = appDatabase.taskQueries.selectAll()
        .asFlow()
        .mapToList(Dispatchers.IO)

    fun addTask(title: String, description: String?) {
        appDatabase.taskQueries.insertTask(title, description)
    }
}