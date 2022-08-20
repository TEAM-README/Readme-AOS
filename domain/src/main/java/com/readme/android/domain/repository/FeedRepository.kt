package com.readme.android.domain.repository

import com.readme.android.domain.entity.response.DomainDetailFeedResponse
import com.readme.android.domain.entity.response.DomainHomeFeedResponse

interface FeedRepository {

    suspend fun getHomeFeed(filters: String): Result<DomainHomeFeedResponse>

    suspend fun getDetailFeed(int: Int): Result<DomainDetailFeedResponse>
}