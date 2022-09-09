package com.readme.android.data.remote.model.response

data class MyFeedResponse(
    val nickname: String,
    val count: Int,
    val feeds: List<Feed>
)
