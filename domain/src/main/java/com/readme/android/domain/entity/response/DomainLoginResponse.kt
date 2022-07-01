package com.readme.android.domain.entity.response

data class DomainLoginResponse(
    val accessToken: String?,
    val isNewUser: Boolean,
    val nickName: String?
)
