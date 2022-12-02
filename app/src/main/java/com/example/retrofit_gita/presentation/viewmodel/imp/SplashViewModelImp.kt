package com.example.retrofit_gita.presentation.viewmodel.imp

import android.os.CountDownTimer
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.retrofit_gita.data.local.LocalRepositoryImp
import com.example.retrofit_gita.presentation.viewmodel.SplashViewModel

class SplashViewModelImp : ViewModel(), SplashViewModel {
    init {
        val repository = LocalRepositoryImp.getInstance()
        object : CountDownTimer(2000, 1000) {
            override fun onTick(millisUntilFinished: Long) {

            }

            override fun onFinish() {
                val t = repository.getAccessTokens().accessToken
                if (t.isNotEmpty()) {
                    moveToMainScreenLiveData.value = Unit
                } else {
                    moveToLoginScreenLiveData.value = Unit
                }
            }
        }.start()
    }

    override val moveToLoginScreenLiveData = MutableLiveData<Unit>()
    override val moveToMainScreenLiveData = MutableLiveData<Unit>()
}