package com.example.retrofit_gita.data.network.models.request

data class SigUpRequest(
    val phone:String,
    val password:String,
    val lastName:String,
    val firstName:String
)