package com.example.retrofit_gita.domain

import com.example.retrofit_gita.data.network.models.response.ActionBookResponse
import com.example.retrofit_gita.data.network.models.request.AddBookRequest
import com.example.retrofit_gita.data.network.models.response.BookResponse
import com.example.retrofit_gita.utils.EventListener

interface BookRepository {

    fun addBook(addBookRequest: AddBookRequest, event: EventListener<BookResponse>)
    fun updateBook(addBookRequest: AddBookRequest, event: EventListener<AddBookRequest>)
    fun getAllBooks(event: EventListener<List<BookResponse>>)
    fun deleteBook(bookId: Int, event: EventListener<ActionBookResponse>)
    fun addToFavourite(bookId: Int, event: EventListener<ActionBookResponse>)
}