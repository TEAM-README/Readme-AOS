package com.readme.android.domain.repository

import com.readme.android.domain.entity.MyPageInfo

interface UserRepository {
    suspend fun getMyPageInfo(): Result<MyPageInfo>

    suspend fun deleteUser(): Result<String>
}
