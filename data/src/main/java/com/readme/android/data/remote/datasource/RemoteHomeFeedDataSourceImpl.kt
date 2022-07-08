package com.readme.android.data.remote.datasource

import com.readme.android.data.remote.calladapter.NetworkState
import com.readme.android.data.remote.model.response.BaseResponse
import com.readme.android.data.remote.model.response.HomeFeedResponse
import com.readme.android.data.remote.service.FeedService
import javax.inject.Inject

class RemoteHomeFeedDataSourceImpl @Inject constructor(
    private val feedService: FeedService
) : RemoteHomeFeedDataSource {
    override suspend fun getHomeFeedList(filters: String): NetworkState<BaseResponse<HomeFeedResponse>> =
        feedService.getHomeFeedList(filters)
}