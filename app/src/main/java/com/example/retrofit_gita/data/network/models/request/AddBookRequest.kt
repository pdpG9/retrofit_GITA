package com.example.retrofit_gita.data.network.models.request

data class AddBookRequest(
    val id:Int,
    val title:String,
    val author:String,
    val description:String,
    val pageCount:Int
)
