package com.example.retrofit_gita.presentation.viewmodel

import androidx.lifecycle.LiveData

interface SplashViewModel {
    val moveToLoginScreenLiveData:LiveData<Unit>
    val moveToMainScreenLiveData:LiveData<Unit>
}