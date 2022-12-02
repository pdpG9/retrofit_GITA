package com.example.retrofit_gita.presentation.viewmodel.imp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.retrofit_gita.data.local.LocalRepositoryImp
import com.example.retrofit_gita.data.network.models.request.SignInRequest
import com.example.retrofit_gita.data.network.models.response.SignUpResponse
import com.example.retrofit_gita.domain.imp.UserRepositoryImp
import com.example.retrofit_gita.presentation.viewmodel.LoginViewModel
import com.example.retrofit_gita.utils.EventListener

class LoginViewModelImp : ViewModel(), LoginViewModel {
    private val repository = UserRepositoryImp.getInstance()
    private val localRepository = LocalRepositoryImp.getInstance()
    override fun clickLogin(phone: String, password: String) {
     changeButtonStateLiveData.value = false
        repository.signIn(SignInRequest(phone,password),object :EventListener<SignUpResponse>{
            override fun success(data: SignUpResponse) {
                localRepository.setVerifyType(false)
                moveToVerifyScreenLiveData.value = Unit
            }

            override fun error(message: String) {
                messageLiveData.value = message
            }

            override fun loading(boolean: Boolean) {
                showProgressLiveData.value = boolean
            }

        })
    }

    override fun clickRegistration() {
        moveToRegistrationLiveData.value = Unit
    }


    override val moveToRegistrationLiveData = MutableLiveData<Unit>()
    override val moveToVerifyScreenLiveData = MutableLiveData<Unit>()
    override val noConnectionLiveData = MutableLiveData<Unit>()
    override val changeButtonStateLiveData = MutableLiveData<Boolean>()
    override val showProgressLiveData = MutableLiveData<Boolean>()
    override val errorInputNameLiveData = MutableLiveData<String>()
    override val errorInputPasswordLiveData = MutableLiveData<String>()
    override val messageLiveData = MutableLiveData<String>()
}