package com.readme.android.domain.entity.response

import com.readme.android.domain.entity.UserInfo

data class DomainSignUpResponse(
    val accessToken: String,
    val userInfo: UserInfo
)
