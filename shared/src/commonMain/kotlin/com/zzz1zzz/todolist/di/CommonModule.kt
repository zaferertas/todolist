package com.zzz1zzz.todolist.di

import com.zzz1zzz.todolist.TasksRepository
import com.zzz1zzz.todolist.TasksViewModel
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val commonModule = module {
    singleOf(::TasksRepository)
    singleOf(::TasksViewModel)
}