package com.example.retrofit_gita.presentation.viewmodel.imp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.retrofit_gita.data.network.models.response.ActionBookResponse
import com.example.retrofit_gita.data.network.models.response.BookResponse
import com.example.retrofit_gita.domain.imp.BookRepositoryImp
import com.example.retrofit_gita.presentation.viewmodel.InfoViewModel
import com.example.retrofit_gita.utils.EventListener

class InfoViewModelImp : ViewModel(), InfoViewModel {
    private val repository = BookRepositoryImp.getInstance()
    private lateinit var currentBook: BookResponse
    override fun clickBack() {
        moveToBackLiveData.value = Unit
    }

    override fun clickLike() {
        changeStateLikeButtonLiveData.value = false
        changeLikeLiveData.value = !changeLikeLiveData.value!!
        currentBook.fav = changeLikeLiveData.value!!
        repository.addToFavourite(currentBook.id, object : EventListener<ActionBookResponse> {
            override fun success(data: ActionBookResponse) {
                messageLiveData.value = data.message
                changeStateLikeButtonLiveData.value = true
            }

            override fun error(message: String) {
                messageLiveData.value = message
                changeStateLikeButtonLiveData.value = true
            }

            override fun loading(boolean: Boolean) {
                showProgressLiveData.value = boolean
                changeStateLikeButtonLiveData.value = true
            }
        })
    }

    override fun load(book: BookResponse) {
        currentBook = book
        titleLiveData.value = book.title
        authorLiveData.value = book.author
        descriptionLiveData.value = book.description
        changeLikeLiveData.value = book.fav
    }

    override val changeLikeLiveData = MutableLiveData<Boolean>()
    override val imageBookLiveData = MutableLiveData<String>()
    override val titleLiveData = MutableLiveData<String>()
    override val authorLiveData = MutableLiveData<String>()
    override val descriptionLiveData = MutableLiveData<String>()
    override val moveToBackLiveData = MutableLiveData<Unit>()
    override val changeStateLikeButtonLiveData = MutableLiveData<Boolean>()
    override val messageLiveData = MutableLiveData<String>()
    override val showProgressLiveData = MutableLiveData<Boolean>()
}