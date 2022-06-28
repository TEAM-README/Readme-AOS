package com.readme.android.data.remote.model.response

import com.google.gson.annotations.SerializedName

data class SignUpResponse(
    @SerializedName("accessToken")
    val accessToken: String,
)
