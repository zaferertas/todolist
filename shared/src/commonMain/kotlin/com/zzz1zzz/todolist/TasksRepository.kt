package com.zzz1zzz.todolist

class TasksRepository() {

   private val dummyTasksList = mutableListOf(
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
    fun getTasks() = dummyTasksList

    fun addTask(name: String, description: String?) {
        dummyTasksList.add(
            Task(
                id = 10,
                title = name,
                description = description,
                isCompleted = false
            )
        )
    }
}