package com.readme.android.data.remote.model.response


import com.google.gson.annotations.SerializedName

data class PostFeedResponse(
    @SerializedName("createdAt")
    val createdAt: String,
    @SerializedName("id")
    val id: Int
)
