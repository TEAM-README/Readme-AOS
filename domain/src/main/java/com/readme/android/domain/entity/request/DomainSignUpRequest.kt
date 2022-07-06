package com.readme.android.domain.entity.request

data class DomainSignUpRequest(
    val platform: String,
    val socialToken: String,
    val nickname: String
)
