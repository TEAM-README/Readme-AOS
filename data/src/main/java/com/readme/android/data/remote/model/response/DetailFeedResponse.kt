package com.readme.android.data.remote.model.response

import com.google.gson.annotations.SerializedName

data class DetailFeedResponse(
    @SerializedName("feed")
    val feed: Feed
)
