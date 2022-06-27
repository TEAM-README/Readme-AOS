package com.readme.android.domain.repository

import com.readme.android.domain.entity.request.DomainLoginRequest
import com.readme.android.domain.entity.response.DomainLoginResponse

interface LoginRepository {

    fun getAccessToken(): String

    fun saveAccessToken(accessToken: String)

    fun saveUserNickname(userNickname: String)

    fun getUserNickname(): String

    fun removeAccessToken()

    fun removeUserNickname()

    suspend fun postLogin(loginRequest: DomainLoginRequest):Result<DomainLoginResponse>
}
