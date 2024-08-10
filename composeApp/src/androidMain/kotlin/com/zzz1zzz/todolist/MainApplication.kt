package com.zzz1zzz.todolist

import android.app.Application
import com.zzz1zzz.todolist.di.initKoin
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        initKoin {
            androidLogger()
            androidContext(this@MainApplication)
        }
    }
}