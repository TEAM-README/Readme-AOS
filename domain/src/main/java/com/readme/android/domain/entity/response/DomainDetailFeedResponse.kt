package com.readme.android.domain.entity.response

import com.readme.android.domain.entity.BookInfo
import com.readme.android.domain.entity.FeedInfo

data class DomainDetailFeedResponse(
    val feedInfo: FeedInfo,
    val bookInfo: BookInfo
)
