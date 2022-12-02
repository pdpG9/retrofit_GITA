package com.example.retrofit_gita.domain

import com.example.retrofit_gita.data.network.models.request.SigUpRequest
import com.example.retrofit_gita.data.network.models.request.SignInRequest
import com.example.retrofit_gita.data.network.models.request.VerifyRequest
import com.example.retrofit_gita.data.network.models.response.SignUpResponse
import com.example.retrofit_gita.data.network.models.response.VerifyResponse
import com.example.retrofit_gita.utils.EventListener

interface UserRepository {
    fun sigUpUser(sigUpRequest: SigUpRequest, event: EventListener<SignUpResponse>)
    fun verifySignUpUser(verifyRequest: VerifyRequest, event: EventListener<VerifyResponse>)
    fun verifySignInUser(verifyRequest: VerifyRequest, event: EventListener<VerifyResponse>)
    fun signIn(signInRequest: SignInRequest, event: EventListener<SignUpResponse>)
}