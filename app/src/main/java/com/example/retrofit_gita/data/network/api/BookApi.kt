package com.example.retrofit_gita.data.network.api

import com.example.retrofit_gita.data.network.models.request.AddBookRequest
import com.example.retrofit_gita.data.network.models.response.ActionBookResponse
import com.example.retrofit_gita.data.network.models.response.BookResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT

interface BookApi {

    @POST("book")
    fun addBook(@Header("token")token:String,@Body data: AddBookRequest):Call<BookResponse>

    @GET("books")
    fun getAllBooks(@Header("token")token:String):Call<List<BookResponse>>

    @DELETE("book")
    fun deleteBook(@Header("token") token: String,@Body bookId:Int):Call<ActionBookResponse>

    //I must complate
    @PUT("book")
    fun updateBook(@Header("token") token:String,@Body data:AddBookRequest)

    @POST("book/change-fav")
    fun addToFavourite(@Header("token") token: String,@Body bookId:Int):Call<ActionBookResponse>

}