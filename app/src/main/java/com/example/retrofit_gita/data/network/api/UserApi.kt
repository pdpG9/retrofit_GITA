package com.example.retrofit_gita.data.network.api

import com.example.retrofit_gita.data.network.models.request.SigUpRequest
import com.example.retrofit_gita.data.network.models.request.SignInRequest
import com.example.retrofit_gita.data.network.models.request.VerifyRequest
import com.example.retrofit_gita.data.network.models.response.SignUpResponse
import com.example.retrofit_gita.data.network.models.response.VerifyResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface UserApi {
    @POST("auth/sign-up")
    fun signUpUser(@Body data:SigUpRequest):Call<SignUpResponse>

    @POST("auth/sign-up/verify")
    fun signUpVerify(@Header("token")token:String,@Body data:VerifyRequest):Call<VerifyResponse>

    @POST("auth/sign-in")
    fun signInUser(@Body data:SignInRequest):Call<SignUpResponse>

    @POST("auth/sign-in/verify")
    fun signInVerify(@Header("token")token:String,@Body data:VerifyRequest):Call<VerifyResponse>

}