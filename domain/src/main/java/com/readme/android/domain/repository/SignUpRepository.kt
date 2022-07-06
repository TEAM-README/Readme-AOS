package com.readme.android.domain.repository

import com.readme.android.domain.entity.request.DomainSignUpRequest

interface SignUpRepository {

    suspend fun checkDuplicateNickName(nickName: String): Result<Boolean>

    suspend fun postSignUp(signUpRequest: DomainSignUpRequest): Result<String>

    fun saveAccessToken(accessToken: String)

    fun saveUserNickname(userNickname: String)
}
