package com.readme.android.domain.repository

import com.readme.android.domain.entity.response.DomainDetailFeedResponse
import com.readme.android.domain.entity.response.DomainHomeFeedResponse
import com.readme.android.domain.entity.response.DomainNoDataResponse

interface FeedRepository {

    suspend fun getHomeFeed(filters: String): Result<DomainHomeFeedResponse>

    suspend fun getDetailFeed(feedId: Int): Result<DomainDetailFeedResponse>

    suspend fun deleteFeed(feedId: Int): Result<DomainNoDataResponse>
}