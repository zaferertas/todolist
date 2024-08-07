package com.zzz1zzz.todolist

data class Task(
    val id: Int,
    val title: String,
    val description: String?,
    val isCompleted: Boolean,
)
