package com.zzz1zzz.todolist.di

import com.zzz1zzz.todolist.AppDatabase
import com.zzz1zzz.todolist.data.TaskDataSource
import com.zzz1zzz.todolist.data.TaskRepositoryImpl
import com.zzz1zzz.todolist.domain.TaskRepository
import com.zzz1zzz.todolist.viewModel.TaskViewModel
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

fun initKoin(appDeclaration: KoinAppDeclaration = {}) =
    startKoin {
        appDeclaration()
        modules(commonModule, platformModule)
    }

@Suppress("unused") // Called from Swift
fun initKoin() = initKoin() {}

val commonModule = module {
    single { AppDatabase(driver = get()) }
    singleOf(::TaskDataSource)
    singleOf(::TaskViewModel)
    singleOf(::TaskRepositoryImpl) { bind<TaskRepository>() }
}

expect val platformModule: Module