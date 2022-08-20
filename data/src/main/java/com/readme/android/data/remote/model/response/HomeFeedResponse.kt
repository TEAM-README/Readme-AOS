package com.readme.android.data.remote.model.response


import com.google.gson.annotations.SerializedName
import com.readme.android.domain.entity.FeedInfo

data class HomeFeedResponse(
    @SerializedName("filters")
    val filters: List<String>,
    @SerializedName("feeds")
    val feeds: List<Feed>
)
