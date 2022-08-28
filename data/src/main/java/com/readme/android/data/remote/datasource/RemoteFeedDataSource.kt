package com.readme.android.data.remote.datasource

import com.readme.android.data.remote.calladapter.NetworkState
import com.readme.android.data.remote.model.response.BaseResponse
import com.readme.android.data.remote.model.response.DetailFeedResponse
import com.readme.android.data.remote.model.response.HomeFeedResponse
import com.readme.android.data.remote.model.response.NoDataResponse

interface RemoteFeedDataSource {

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