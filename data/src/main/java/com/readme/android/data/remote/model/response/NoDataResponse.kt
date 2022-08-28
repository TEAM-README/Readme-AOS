package com.readme.android.data.remote.model.response

import com.google.gson.annotations.SerializedName

data class NoDataResponse(
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: Int,
    @SerializedName("success")
    val success: Boolean
)
