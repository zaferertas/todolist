package com.zzz1zzz.todolist.data

import com.zzz1zzz.todolist.Taskdata
import com.zzz1zzz.todolist.domain.DateTimeService
import com.zzz1zzz.todolist.domain.Task
import com.zzz1zzz.todolist.domain.formatDateTime

fun Taskdata.asDomain(dateTimeService: DateTimeService) = Task(
    id = id,
    title = title,
    description = description,
    isCompleted = isCompleted,
    createdAt = with(dateTimeService) {
        formatDateTime(createdAt.toLocalDateTime())
    },
    completeAt = with(dateTimeService) {
        completedAt?.let {
            formatDateTime(it.toLocalDateTime())
        }
    },
)
