package com.readme.android.data.remote.model.request

import com.google.gson.annotations.SerializedName
import retrofit2.http.Multipart

data class LoginRequest(
    @SerializedName("platform")
    val platform: String,
    @SerializedName("socialToken")
    val socialToken: String
)

