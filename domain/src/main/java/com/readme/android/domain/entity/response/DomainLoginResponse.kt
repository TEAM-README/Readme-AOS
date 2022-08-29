package com.readme.android.domain.entity.response

import com.readme.android.domain.entity.UserInfo

data class DomainLoginResponse(
    val accessToken: String?,
    val isNewUser: Boolean,
    val userInfo: UserInfo?
)

