package com.example.retrofit_gita.presentation.viewmodel.imp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.retrofit_gita.data.network.models.response.BookResponse
import com.example.retrofit_gita.domain.imp.BookRepositoryImp
import com.example.retrofit_gita.presentation.viewmodel.MainViewModel
import com.example.retrofit_gita.utils.EventListener

class MainViewModelImp : ViewModel(), MainViewModel {
    private val repository = BookRepositoryImp.getInstance()
    private var listBook = ArrayList<BookResponse>()

    init {
        loadBooks()
    }

    private fun loadBooks() {
        repository.getAllBooks(object : EventListener<List<BookResponse>> {
            override fun success(data: List<BookResponse>) {
                booksLiveData.value = data
                listBook.clear()
                listBook.addAll(data)
            }

            override fun error(message: String) {
                messageLiveData.value = message
            }

            override fun loading(boolean: Boolean) {
                showProgressLiveData.value = boolean
            }

        })
    }

    override fun clickMenu() {

    }

    override fun clickNotify() {

    }

    override fun clickItemBook(bookId: Int) {
        moveToBookInfoLiveData.value = listBook[bookId]
    }

    override fun clickAddBook() {
        moveToAddButtonLiveData.value = Unit
    }

    override val moveToAddButtonLiveData = MutableLiveData<Unit>()
    override val moveToBookInfoLiveData = MutableLiveData<BookResponse>()
    override val booksLiveData = MutableLiveData<List<BookResponse>>()
    override val showProgressLiveData = MutableLiveData<Boolean>()
    override val messageLiveData = MutableLiveData<String>()
}