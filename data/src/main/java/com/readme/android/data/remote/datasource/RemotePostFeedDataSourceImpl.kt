package com.readme.android.data.remote.datasource

import com.readme.android.data.remote.calladapter.NetworkState
import com.readme.android.data.remote.model.request.PostFeedRequest
import com.readme.android.data.remote.model.response.BaseResponse
import com.readme.android.data.remote.model.response.PostFeedResponse
import com.readme.android.data.remote.service.PostFeedService
import javax.inject.Inject

class RemotePostFeedDataSourceImpl @Inject constructor(
    private val postFeedService: PostFeedService
) : RemotePostFeedDataSource {
    override suspend fun postFeed(postFeedRequest: PostFeedRequest): NetworkState<BaseResponse<PostFeedResponse>> =
        postFeedService.postFeed(postFeedRequest)
}
