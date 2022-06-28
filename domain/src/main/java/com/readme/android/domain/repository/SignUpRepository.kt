package com.readme.android.domain.repository

interface SignUpRepository {

    suspend fun checkDuplicateNickName(nickName: String): Result<Boolean>
}
