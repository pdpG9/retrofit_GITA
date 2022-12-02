package com.example.retrofit_gita.presentation.viewmodel

import androidx.lifecycle.LiveData

interface LoginViewModel {
    fun clickLogin(phone:String, password:String)
    fun clickRegistration()

    val moveToRegistrationLiveData:LiveData<Unit>
    val moveToVerifyScreenLiveData:LiveData<Unit>
    val noConnectionLiveData:LiveData<Unit>
    val changeButtonStateLiveData:LiveData<Boolean>
    val showProgressLiveData:LiveData<Boolean>
    val errorInputNameLiveData:LiveData<String>
    val errorInputPasswordLiveData:LiveData<String>
    val messageLiveData:LiveData<String>

}