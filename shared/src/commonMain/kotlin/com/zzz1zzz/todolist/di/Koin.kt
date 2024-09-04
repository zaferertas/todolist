package com.zzz1zzz.todolist.di

import com.zzz1zzz.todolist.AppDatabase
import com.zzz1zzz.todolist.Taskdata
import com.zzz1zzz.todolist.data.TaskDataSource
import com.zzz1zzz.todolist.data.TaskRepositoryImpl
import com.zzz1zzz.todolist.data.adapter.InstantSqlDelightAdapter
import com.zzz1zzz.todolist.domain.DateTimeService
import com.zzz1zzz.todolist.domain.TaskRepository
import com.zzz1zzz.todolist.viewModel.AddTaskViewModel
import com.zzz1zzz.todolist.viewModel.MainViewModel
import com.zzz1zzz.todolist.viewModel.TaskDetailsViewModel
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
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
    single {
        AppDatabase(
            driver = get(),
            TaskdataAdapter = Taskdata.Adapter(
                createdAtAdapter = InstantSqlDelightAdapter,
                completedAtAdapter = InstantSqlDelightAdapter,
            )
        )
    }
    factoryOf(::MainViewModel)
    factoryOf(::AddTaskViewModel)
    factoryOf(::TaskDetailsViewModel)
    singleOf(::TaskDataSource)
    singleOf(::TaskRepositoryImpl) { bind<TaskRepository>() }
    singleOf(::DateTimeService)
}

expect val platformModule: Module