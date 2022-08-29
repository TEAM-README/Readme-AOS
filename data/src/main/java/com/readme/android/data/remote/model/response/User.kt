package com.readme.android.data.remote.model.response

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("nickname")
    val nickname: String?
)
