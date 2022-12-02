package com.example.retrofit_gita.data.network.models.response

data class BookResponse(
    val id:Int,
    val title:String,
    val author:String,
    val description:String,
    val pageCount:Int,
    var fav:Boolean
):java.io.Serializable
