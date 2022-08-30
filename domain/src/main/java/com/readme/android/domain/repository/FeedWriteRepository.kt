package com.readme.android.domain.repository

import com.readme.android.domain.entity.request.DomainPostFeedRequest

interface FeedWriteRepository {

    fun getUserNickName(): String

    suspend fun postFeed(postFeedRequest: DomainPostFeedRequest): Result<Boolean>
}
