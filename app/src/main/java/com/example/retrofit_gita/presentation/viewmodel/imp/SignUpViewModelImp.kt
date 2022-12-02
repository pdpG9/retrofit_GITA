package com.example.retrofit_gita.presentation.viewmodel.imp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.retrofit_gita.data.local.LocalRepositoryImp
import com.example.retrofit_gita.data.network.models.request.SigUpRequest
import com.example.retrofit_gita.data.network.models.response.SignUpResponse
import com.example.retrofit_gita.domain.imp.UserRepositoryImp
import com.example.retrofit_gita.presentation.viewmodel.SignUpViewModel
import com.example.retrofit_gita.utils.EventListener

class SignUpViewModelImp : ViewModel(), SignUpViewModel {
    private val repository = UserRepositoryImp.getInstance()
    private val localRepository = LocalRepositoryImp.getInstance()
    override fun clickCreateAccount(
        firstName: String,
        lastName: String,
        phone: String,
        password: String,
        passwordReturn: String
    ) {
        changeButtonStateLiveData.value = false
        if (firstName.length < 3) errorInputFirstNameLiveData.value =
            "First name must be length than 3!"
        if (lastName.length < 3) errorInputLastNameLiveData.value =
            "Last name must be length than 3!"
        if (password.length < 6) errorInputPasswordLiveData.value =
            "Password must be length than 6!"
        if (phone.length != 13) errorInputPhoneLiveData.value = "Phone is invalid!"
        if (passwordReturn != password) errorInputReturnPasswordLiveData.value =
            "Confirm password must be equal password!"
        if (firstName.length >= 3 && lastName.length >= 3 && password.length >= 6 && password == passwordReturn && phone.length == 13) {
            repository.sigUpUser(SigUpRequest(phone, password, lastName, firstName),
                object : EventListener<SignUpResponse> {
                    override fun success(data: SignUpResponse) {
                        localRepository.setVerifyType(true)
                        moveToVerifyScreenLiveData.value = Unit
                        changeButtonStateLiveData.value = true
                    }

                    override fun error(message: String) {
                        messageLiveData.value = message
                        changeButtonStateLiveData.value = true
                    }

                    override fun loading(boolean: Boolean) {
                        showProgressLiveData.value = boolean
                        changeButtonStateLiveData.value = true
                    }

                })
        } else {
            changeButtonStateLiveData.value = true
        }
    }

    override fun clickLogin() {
        moveToLoginLiveData.value = Unit
    }

    override val moveToLoginLiveData = MutableLiveData<Unit>()
    override val moveToVerifyScreenLiveData = MutableLiveData<Unit>()
    override val noConnectionLiveData = MutableLiveData<Unit>()
    override val changeButtonStateLiveData = MutableLiveData<Boolean>()
    override val showProgressLiveData = MutableLiveData<Boolean>()
    override val errorInputFirstNameLiveData = MutableLiveData<String>()
    override val errorInputLastNameLiveData = MutableLiveData<String>()
    override val errorInputPhoneLiveData = MutableLiveData<String>()
    override val errorInputPasswordLiveData = MutableLiveData<String>()
    override val errorInputReturnPasswordLiveData = MutableLiveData<String>()
    override val messageLiveData = MutableLiveData<String>()
}