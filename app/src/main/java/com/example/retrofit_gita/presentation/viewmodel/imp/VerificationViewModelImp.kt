package com.example.retrofit_gita.presentation.viewmodel.imp

import android.os.CountDownTimer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.retrofit_gita.data.local.LocalRepositoryImp
import com.example.retrofit_gita.data.network.models.request.VerifyRequest
import com.example.retrofit_gita.data.network.models.response.VerifyResponse
import com.example.retrofit_gita.domain.imp.UserRepositoryImp
import com.example.retrofit_gita.presentation.viewmodel.VerificationViewModel
import com.example.retrofit_gita.utils.EventListener

class VerificationViewModelImp : ViewModel(), VerificationViewModel {
    private val repository = UserRepositoryImp.getInstance()
    private val localRepository = LocalRepositoryImp.getInstance()

    override fun clickVerify(code: String) {
        if (localRepository.isSignUpType()){
        repository.verifySignUpUser(VerifyRequest(code),object :EventListener<VerifyResponse>{
            override fun success(data: VerifyResponse) {
                if (data.accessToken.isNotEmpty())
                moveToMainScreenLiveData.value = Unit
                else messageLiveData.value = "Token is empty"
            }

            override fun error(message: String) {
                messageLiveData.value = message
            }

            override fun loading(boolean: Boolean) {
                showProgressLiveData.value = boolean
            }

        })
        }else{
            repository.verifySignInUser(VerifyRequest(code),object :EventListener<VerifyResponse>{
                override fun success(data: VerifyResponse) {
                    if (data.accessToken.isNotEmpty())
                        moveToMainScreenLiveData.value = Unit
                    else messageLiveData.value = "Token is empty"
                }

                override fun error(message: String) {
                    messageLiveData.value = message
                }

                override fun loading(boolean: Boolean) {
                    showProgressLiveData.value = boolean
                }

            })

        }
    }

    override fun clickSendAgain() {
        changeVisibilityProgressLiveData.value = true

        startTimer()

    }

    init {
        startTimer()
    }

    private fun startTimer() {
        var time = 60
        object : CountDownTimer(60000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                time--
                changeProgressLiveData.value = (100 * time) / 60
                changeTimerTextLiveDAta.value = time.toString()
            }

            override fun onFinish() {
                changeVisibilityProgressLiveData.value = false
            }

        }.start()
    }

    override val changeVisibilityProgressLiveData = MutableLiveData<Boolean>()
    override val changeProgressLiveData = MutableLiveData<Int>()
    override val moveToMainScreenLiveData = MutableLiveData<Unit>()
    override val changeTimerTextLiveDAta = MutableLiveData<String>()
    override val showProgressLiveData = MutableLiveData<Boolean>()
    override val messageLiveData = MutableLiveData<String>()
}