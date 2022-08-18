package com.readme.android.domain.entity.response

import com.readme.android.domain.entity.Feed

data class DomainHomeFeedResponse(
    val filters: List<String>,
    val feeds: List<Feed>
)