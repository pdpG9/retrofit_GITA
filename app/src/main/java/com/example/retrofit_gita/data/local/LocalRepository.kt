package com.example.retrofit_gita.data.local

import com.example.retrofit_gita.data.network.models.response.VerifyResponse

interface LocalRepository {
    fun saveTemporaryToken(token:String)
    fun getTemporaryToken():String
    fun saveAccessTokens(data:VerifyResponse)
    fun getAccessTokens():VerifyResponse
    fun deleteTemporaryToken()
    fun deleteAccessTokens()
    fun setVerifyType(b:Boolean)
    fun isSignUpType():Boolean
}