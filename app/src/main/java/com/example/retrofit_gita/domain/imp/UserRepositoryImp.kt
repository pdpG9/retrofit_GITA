package com.example.retrofit_gita.domain.imp

import com.example.retrofit_gita.data.local.LocalRepositoryImp
import com.example.retrofit_gita.data.network.ApiClient
import com.example.retrofit_gita.data.network.api.UserApi
import com.example.retrofit_gita.data.network.models.request.SigUpRequest
import com.example.retrofit_gita.data.network.models.request.SignInRequest
import com.example.retrofit_gita.data.network.models.request.VerifyRequest
import com.example.retrofit_gita.data.network.models.response.SignUpResponse
import com.example.retrofit_gita.data.network.models.response.VerifyResponse
import com.example.retrofit_gita.domain.UserRepository
import com.example.retrofit_gita.utils.EventListener
import com.example.retrofit_gita.utils.myLog
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserRepositoryImp private constructor() : UserRepository {
    private val userApi = ApiClient.retrofit.create(UserApi::class.java)
    private val localRepository = LocalRepositoryImp.getInstance()

    companion object {
        private var instance: UserRepository? = null
        fun getInstance(): UserRepository {
            if (instance == null) instance = UserRepositoryImp()
            return instance!!
        }
    }

    override fun sigUpUser(
        sigUpRequest: SigUpRequest,
        event: EventListener<SignUpResponse>
    ) {
        event.loading(true)
        sigUpRequest.toString().myLog()
        userApi.signUpUser(sigUpRequest).enqueue(object : Callback<SignUpResponse> {
            override fun onResponse(
                call: Call<SignUpResponse>,
                response: Response<SignUpResponse>
            ) {
                event.loading(false)
                if (response.isSuccessful) {
                    response.body()?.let {
                        localRepository.saveTemporaryToken(it.token)
                        event.success(it)
                    }
                } else {
                    event.error(response.message())
                }
            }

            override fun onFailure(call: Call<SignUpResponse>, t: Throwable) {
                event.loading(false)
                event.error(t.message ?: "error sigUpUser")
            }

        })

    }

    override fun signIn(signInRequest: SignInRequest, event: EventListener<SignUpResponse>) {
        event.loading(true)
        userApi.signInUser(signInRequest).enqueue(object : Callback<SignUpResponse> {
            override fun onResponse(
                call: Call<SignUpResponse>,
                response: Response<SignUpResponse>
            ) {
                event.loading(false)
                if (response.isSuccessful) {
                    response.body()?.let {
                        localRepository.saveTemporaryToken(it.token)
                        event.success(it)
                    }
                } else {
                    event.error(response.message())
                }
            }

            override fun onFailure(call: Call<SignUpResponse>, t: Throwable) {
                event.loading(false)
                event.error(t.message ?: "error signIn")
            }

        })
    }

    override fun verifySignUpUser(
        verifyRequest: VerifyRequest,
        event: EventListener<VerifyResponse>
    ) {
        event.loading(true)
        val token = localRepository.getTemporaryToken()
        if (token.isEmpty()) {
            event.error("Token is empty")
            return
        }
        userApi.signUpVerify(token, verifyRequest).enqueue(object : Callback<VerifyResponse> {
            override fun onResponse(
                call: Call<VerifyResponse>,
                response: Response<VerifyResponse>
            ) {
                event.loading(false)
                if (response.isSuccessful) {
                    response.body()?.let {
                        localRepository.saveAccessTokens(it)
                        event.success(it)
                    }
                } else {
                    event.error(response.message())
                }
            }

            override fun onFailure(call: Call<VerifyResponse>, t: Throwable) {
                event.loading(false)
                event.error(t.message ?: "error verifySignUpUser")
            }

        })
    }

    override fun verifySignInUser(

        verifyRequest: VerifyRequest,
        event: EventListener<VerifyResponse>
    ) {
        event.loading(true)
        val token = localRepository.getTemporaryToken()
        if (token.isEmpty()) {
            event.error("Token is empty")
            return
        }
        userApi.signInVerify(token, verifyRequest).enqueue(object : Callback<VerifyResponse> {
            override fun onResponse(
                call: Call<VerifyResponse>,
                response: Response<VerifyResponse>
            ) {
                event.loading(false)
                if (response.isSuccessful) {
                    response.body()?.let {
                        localRepository.saveAccessTokens(it)
                        event.success(it)
                    }
                } else {
                    event.error(response.message())
                }
            }

            override fun onFailure(call: Call<VerifyResponse>, t: Throwable) {
                event.loading(false)
                event.error(t.message ?: "error verifySignInUser")
            }

        })
    }
}