package com.zzz1zzz.sharedui.navigation
import kotlinx.serialization.Serializable

sealed interface Route {
    @Serializable
    data object Main : Route

    @Serializable
    data object AddTask : Route

    @Serializable
    data class TaskDetails(val taskId: Long) : Route
}