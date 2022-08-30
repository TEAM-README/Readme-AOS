package com.readme.android.data.remote.model.response

import com.google.gson.annotations.SerializedName

data class Feed(
    @SerializedName("id")
    val id: Int,
    @SerializedName("categoryName")
    val categoryName: String,
    @SerializedName("sentence")
    val sentence: String,
    @SerializedName("feeling")
    val feeling: String,
    @SerializedName("reportedCount")
    val reportedCount: Int,
    @SerializedName("createdAt")
    val createdAt: String,
    @SerializedName("isDeleted")
    val isDeleted: Boolean,
    @SerializedName("book")
    val book: Book?,
    @SerializedName("user")
    val user: User?
)