package com.example.retrofit_gita.data.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    private val myClient = OkHttpClient.Builder()
        .build()

    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("http://143.198.48.222:82/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}