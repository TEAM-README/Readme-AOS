package com.readme.android.data.remote.datasource

import com.readme.android.data.remote.calladapter.NetworkState
import com.readme.android.data.remote.model.request.PostFeedRequest
import com.readme.android.data.remote.model.response.BaseResponse
import com.readme.android.data.remote.model.response.DetailFeedResponse
import com.readme.android.data.remote.model.response.HomeFeedResponse
import com.readme.android.data.remote.model.response.PostFeedResponse
import com.readme.android.data.remote.service.FeedService
import javax.inject.Inject

class RemoteFeedDataSourceImpl @Inject constructor(
    private val feedService: FeedService
) : RemoteFeedDataSource {

    override suspend fun postFeed(postFeedRequest: PostFeedRequest): NetworkState<BaseResponse<PostFeedResponse>> =
        feedService.postFeed(postFeedRequest)

    override suspend fun getHomeFeedList(filters: String): NetworkState<BaseResponse<HomeFeedResponse>> =
        feedService.getHomeFeedList(filters)

    override suspend fun getDetailFeed(feedId: Int): NetworkState<BaseResponse<DetailFeedResponse>> =
        feedService.getDetailFeed(feedId)
}