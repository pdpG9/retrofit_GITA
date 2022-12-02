package com.example.retrofit_gita.presentation.viewmodel

import androidx.lifecycle.LiveData
import com.example.retrofit_gita.data.network.models.response.BookResponse

interface MainViewModel {
    fun clickMenu()
    fun clickNotify()
    fun clickItemBook(bookId: Int)
    fun clickAddBook()

    val moveToAddButtonLiveData: LiveData<Unit>
    val moveToBookInfoLiveData: LiveData<BookResponse>
    val booksLiveData: LiveData<List<BookResponse>>
    val showProgressLiveData:LiveData<Boolean>
    val messageLiveData:LiveData<String>
}