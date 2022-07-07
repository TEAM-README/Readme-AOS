package com.readme.android.data.remote.model.response


import kotlinx.serialization.SerialName

data class HomeFeedResponse(
    @SerialName("filters")
    val filters: List<String>,
    @SerialName("feeds")
    val feeds: List<HomeFeed>
)

data class HomeFeed(
    @SerialName("id")
    val id: Int,
    @SerialName("categoryName")
    val categoryName: String,
    @SerialName("sentence")
    val sentence: String,
    @SerialName("feeling")
    val feeling: String,
    @SerialName("reportedCount")
    val reportedCount: Int,
    @SerialName("createdAt")
    val createdAt: String,
    @SerialName("isDeleted")
    val isDeleted: Boolean,
    @SerialName("book")
    val book: Book,
    @SerialName("user")
    val user: User
)
