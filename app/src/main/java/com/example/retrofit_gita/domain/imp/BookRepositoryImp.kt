package com.example.retrofit_gita.domain.imp

import com.example.retrofit_gita.data.local.LocalRepositoryImp
import com.example.retrofit_gita.data.network.ApiClient
import com.example.retrofit_gita.data.network.api.BookApi
import com.example.retrofit_gita.data.network.models.request.AddBookRequest
import com.example.retrofit_gita.data.network.models.response.ActionBookResponse
import com.example.retrofit_gita.data.network.models.response.BookResponse
import com.example.retrofit_gita.domain.BookRepository
import com.example.retrofit_gita.utils.EventListener
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BookRepositoryImp private constructor() : BookRepository {
    private val bookApi = ApiClient.retrofit.create(BookApi::class.java)
    private val localRepository = LocalRepositoryImp.getInstance()

    companion object {
        private var instance: BookRepository? = null
        fun getInstance(): BookRepository {
            if (instance == null) instance = BookRepositoryImp()
            return instance!!
        }
    }

    override fun addBook(addBookRequest: AddBookRequest, event: EventListener<BookResponse>) {
        event.loading(true)
        val token = localRepository.getAccessTokens()
        if (token.accessToken.isEmpty()) {
            event.error("Token is empty")
            return
        }
        bookApi.addBook(token.accessToken, addBookRequest).enqueue(object : Callback<BookResponse> {
            override fun onResponse(call: Call<BookResponse>, response: Response<BookResponse>) {
                event.loading(false)
                if (response.isSuccessful) {
                    response.body()?.let { event.success(it) }
                } else {
                    event.error(response.message())
                }
            }

            override fun onFailure(call: Call<BookResponse>, t: Throwable) {
                event.loading(false)
                event.error(t.message ?: "error addBook")
            }
        })

    }

    override fun updateBook(
        addBookRequest: AddBookRequest,
        event: EventListener<AddBookRequest>
    ) {
        event.loading(true)
        val token = localRepository.getAccessTokens()
        if (token.accessToken.isEmpty()) {
            event.error("Token is empty")
            return
        }
        bookApi.updateBook(token.accessToken, addBookRequest)

    }

    override fun getAllBooks(event: EventListener<List<BookResponse>>) {
        event.loading(true)
        val token = localRepository.getAccessTokens()
        if (token.accessToken.isEmpty()) {
            event.error("Token is empty")
            return
        }
        bookApi.getAllBooks(token.accessToken).enqueue(object : Callback<List<BookResponse>> {
            override fun onResponse(
                call: Call<List<BookResponse>>,
                response: Response<List<BookResponse>>
            ) {
                event.loading(false)
                if (response.isSuccessful) {
                    response.body()?.let { event.success(it) }
                } else {
                    event.error(response.message())
                }
            }

            override fun onFailure(call: Call<List<BookResponse>>, t: Throwable) {
                event.loading(false)
                event.error(t.message ?: "error getAllBooks")
            }

        })
    }

    override fun deleteBook(bookId: Int, event: EventListener<ActionBookResponse>) {
        event.loading(true)
        val token = localRepository.getAccessTokens()
        if (token.accessToken.isEmpty()) {
            event.error("Token is empty")
            return
        }
        bookApi.deleteBook(token.accessToken, bookId)
            .enqueue(object : Callback<ActionBookResponse> {
                override fun onResponse(
                    call: Call<ActionBookResponse>,
                    response: Response<ActionBookResponse>
                ) {
                    event.loading(false)
                    if (response.isSuccessful) {
                        response.body()?.let { event.success(it) }
                    } else {
                        event.error(response.message())
                    }
                }

                override fun onFailure(call: Call<ActionBookResponse>, t: Throwable) {
                    event.loading(false)
                    event.error(t.message ?: "error deleteBook")
                }

            })

    }

    override fun addToFavourite(bookId: Int, event: EventListener<ActionBookResponse>) {
        event.loading(true)
        val token = localRepository.getAccessTokens()
        if (token.accessToken.isEmpty()) {
            event.error("Token is empty")
            return
        }
        bookApi.addToFavourite(token.accessToken, bookId)
            .enqueue(object : Callback<ActionBookResponse> {
                override fun onResponse(
                    call: Call<ActionBookResponse>,
                    response: Response<ActionBookResponse>
                ) {
                    event.loading(false)
                    if (response.isSuccessful) {
                        response.body()?.let { event.success(it) }
                    } else {
                        event.error(response.message())
                    }
                }

                override fun onFailure(call: Call<ActionBookResponse>, t: Throwable) {
                    event.loading(false)
                    event.error(t.message ?: "error addToFavourite")
                }

            })
    }

}