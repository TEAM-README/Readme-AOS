package com.readme.android.domain.repository

import com.readme.android.domain.entity.request.DomainSignUpRequest
import com.readme.android.domain.entity.response.DomainSignUpResponse

interface SignUpRepository {

    suspend fun checkDuplicateNickName(nickName: String): Result<Boolean>

    suspend fun postSignUp(signUpRequest: DomainSignUpRequest): Result<DomainSignUpResponse>

    fun saveAccessToken(accessToken: String)

    fun saveUserNickname(userNickname: String)

    fun saveUserId(userId: Int)
}
