package com.readme.android.data.remote.model.request

import com.google.gson.annotations.SerializedName

data class SignUpRequest(
    @SerializedName("platform")
    val platform: String,
    @SerializedName("socialToken")
    val socialToken: String,
    @SerializedName("nickname")
    val nickname: String,
)
