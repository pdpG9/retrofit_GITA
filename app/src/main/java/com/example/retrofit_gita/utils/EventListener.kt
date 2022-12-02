package com.example.retrofit_gita.utils

interface EventListener<T> {
    fun success(data: T)
    fun error(message: String)
    fun loading(boolean: Boolean)
}