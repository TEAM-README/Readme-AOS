package com.readme.android.data.remote.datasource

import com.readme.android.data.remote.calladapter.NetworkState
import com.readme.android.data.remote.model.request.PostFeedRequest
import com.readme.android.data.remote.model.response.BaseResponse
import com.readme.android.data.remote.model.response.DetailFeedResponse
import com.readme.android.data.remote.model.response.HomeFeedResponse
import com.readme.android.data.remote.model.response.PostFeedResponse
import com.readme.android.data.remote.model.response.NoDataResponse


interface RemoteFeedDataSource {

    suspend fun postFeed(postFeedRequest: PostFeedRequest): NetworkState<BaseResponse<PostFeedResponse>>

    suspend fun getHomeFeedList(
        filters: String
    ): NetworkState<BaseResponse<HomeFeedResponse>>

    suspend fun getDetailFeed(
        feedId: Int
    ): NetworkState<BaseResponse<DetailFeedResponse>>

    suspend fun deleteFeed(
        feedId: Int
    ): NetworkState<NoDataResponse>
}