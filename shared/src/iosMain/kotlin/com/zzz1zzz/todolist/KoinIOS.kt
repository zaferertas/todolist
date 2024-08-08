package com.zzz1zzz.todolist

import com.zzz1zzz.todolist.di.commonModule
import org.koin.core.component.KoinComponent
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

fun initKoin( appDeclaration: KoinAppDeclaration = {}) =
    startKoin {
        appDeclaration()
        modules(commonModule)
    }

@Suppress("unused") // Called from Swift
fun initKoin() = initKoin() {}

@Suppress("unused") // Called from Swift
object KotlinDependencies : KoinComponent {
    fun getTasksViewModel() = getKoin().get<TasksViewModel>()
}
