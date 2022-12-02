package com.example.retrofit_gita.presentation.viewmodel

import androidx.lifecycle.LiveData
import com.example.retrofit_gita.data.network.models.response.BookResponse

interface InfoViewModel {
    fun clickBack()
    fun clickLike()
    fun load(book:BookResponse)

    val changeLikeLiveData:LiveData<Boolean>
    val imageBookLiveData:LiveData<String>
    val titleLiveData:LiveData<String>
    val authorLiveData:LiveData<String>
    val descriptionLiveData:LiveData<String>
    val moveToBackLiveData:LiveData<Unit>
    val changeStateLikeButtonLiveData:LiveData<Boolean>
    val messageLiveData:LiveData<String>
    val showProgressLiveData:LiveData<Boolean>
}