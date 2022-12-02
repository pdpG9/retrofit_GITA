package com.example.retrofit_gita.app

import android.app.Application
import com.example.retrofit_gita.data.local.LocalRepositoryImp

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        LocalRepositoryImp.init(this)
    }

    companion object {
        private val instance = App()
        fun getInstance() = instance
    }
}