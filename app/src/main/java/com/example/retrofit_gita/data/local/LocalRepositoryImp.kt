package com.example.retrofit_gita.data.local

import android.content.Context
import android.content.SharedPreferences
import com.example.retrofit_gita.data.network.models.response.VerifyResponse

class LocalRepositoryImp private constructor() : LocalRepository {
    companion object {
        private const val TEMPORARY_TOKEN = "TEMPORARY_TOKEN"
        private const val ACCESS_TOKEN = "ACCESS_TOKEN"
        private const val REFRESH_TOKEN = "REFRESH_TOKEN"
        private const val VERIFY_TYPE = "VERIFY_TYPE"
        private lateinit var pref: SharedPreferences
        private var instance: LocalRepository? = null
        fun init(context: Context) {
            instance = LocalRepositoryImp()
            pref = context.getSharedPreferences("bookPref", Context.MODE_PRIVATE)
        }

        fun getInstance(): LocalRepository = instance!!
    }

    override fun saveTemporaryToken(token: String) {
        pref.edit().putString(TEMPORARY_TOKEN, token).apply()
    }

    override fun getTemporaryToken(): String {
        return pref.getString(TEMPORARY_TOKEN, "") ?: ""
    }

    override fun saveAccessTokens(data: VerifyResponse) {
        pref.edit().putString(ACCESS_TOKEN, data.accessToken)
            .putString(REFRESH_TOKEN, data.refreshToken).apply()
    }

    override fun getAccessTokens(): VerifyResponse {
        val accessToken = pref.getString(ACCESS_TOKEN, "") ?: ""
        val refreshToken = pref.getString(REFRESH_TOKEN, "") ?: ""
        return VerifyResponse(accessToken, refreshToken)
    }

    override fun deleteTemporaryToken() {
        saveTemporaryToken("")
    }

    override fun deleteAccessTokens() {
        saveAccessTokens(VerifyResponse("", ""))
    }

    override fun setVerifyType(b: Boolean) {
        pref.edit().putBoolean(VERIFY_TYPE, b).apply()
    }

    override fun isSignUpType(): Boolean {
        return pref.getBoolean(VERIFY_TYPE, false)
    }
}