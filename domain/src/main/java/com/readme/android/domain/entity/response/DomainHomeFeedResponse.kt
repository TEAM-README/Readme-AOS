package com.readme.android.domain.entity.response

import com.readme.android.domain.entity.FeedInfo

data class DomainHomeFeedResponse(
    val filters: List<String>,
    val feedInfos: List<FeedInfo>
)