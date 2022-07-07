package com.readme.android.data.remote.model.response

import kotlinx.serialization.SerialName

data class User(
    @SerialName("id")
    val id: Int,
    @SerialName("nickname")
    val nickname: String
)