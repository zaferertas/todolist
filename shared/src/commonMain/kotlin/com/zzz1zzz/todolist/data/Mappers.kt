package com.zzz1zzz.todolist.data

import com.zzz1zzz.todolist.Taskdata
import com.zzz1zzz.todolist.domain.Task

fun Taskdata.asDomain() = Task(
    id = id,
    title = title,
    description = description,
    isCompleted = isCompleted,
    completionDate = completionDateUTC,
)
