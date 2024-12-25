package com.zzz1zzz.sharedui.ui.main

import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource
import todolist.sharedui.generated.resources.Res
import todolist.sharedui.generated.resources.ic_settings_outline
import todolist.sharedui.generated.resources.ic_settings_filled
import todolist.sharedui.generated.resources.ic_list
import todolist.sharedui.generated.resources.screen_name_settings
import todolist.sharedui.generated.resources.screen_name_tasks

enum class BottomNavigationItems(
    val itemId: Int,
    val resourceId: StringResource,
    val icon: DrawableResource,
    val iconSelected: DrawableResource
) {
    TASKS(
        0,
        Res.string.screen_name_tasks,
        Res.drawable.ic_list,
        Res.drawable.ic_list,
    ),
    SETTINGS(
        1,
        Res.string.screen_name_settings,
        Res.drawable.ic_settings_outline,
        Res.drawable.ic_settings_filled,
    ),
}