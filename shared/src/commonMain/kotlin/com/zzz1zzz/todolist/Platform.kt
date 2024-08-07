package com.zzz1zzz.todolist

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform