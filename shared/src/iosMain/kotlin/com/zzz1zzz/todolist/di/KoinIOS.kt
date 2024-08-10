package com.zzz1zzz.todolist.di

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import com.zzz1zzz.todolist.AppDatabase
import com.zzz1zzz.todolist.viewModel.TaskViewModel
import org.koin.core.component.KoinComponent
import org.koin.dsl.module

@Suppress("unused") // Called from Swift
object KotlinDependencies : KoinComponent {
    fun getTaskViewModel() = getKoin().get<TaskViewModel>()
}

actual val platformModule = module {
    single<SqlDriver> {
        NativeSqliteDriver(
            AppDatabase.Schema,
            "todolist.db"
        )
    }
}
