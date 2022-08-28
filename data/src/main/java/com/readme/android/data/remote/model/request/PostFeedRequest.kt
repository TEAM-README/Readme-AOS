package com.readme.android.data.remote.model.request

import com.google.gson.annotations.SerializedName
import com.readme.android.domain.entity.BookInfo

data class PostFeedRequest(
    @SerializedName("categoryName")
    val categoryName: String,
    @SerializedName("sentence")
    val impressiveSentence: String,
    @SerializedName("feeling")
    val feeling: String,
    @SerializedName("book")
    val bookInfo: BookInfo
)
