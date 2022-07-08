package com.readme.android.domain.repository

import com.readme.android.domain.entity.response.DomainHomeFeedResponse

interface FeedRepository {

    suspend fun getHomeFeed(filters: String): Result<DomainHomeFeedResponse>
}