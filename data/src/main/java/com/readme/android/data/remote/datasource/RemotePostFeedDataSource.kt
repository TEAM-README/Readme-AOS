package com.readme.android.data.remote.datasource

import com.readme.android.data.remote.calladapter.NetworkState
import com.readme.android.data.remote.model.request.PostFeedRequest
import com.readme.android.data.remote.model.response.BaseResponse
import com.readme.android.data.remote.model.response.PostFeedResponse

interface RemotePostFeedDataSource {

    suspend fun postFeed(postFeedRequest: PostFeedRequest): NetworkState<BaseResponse<PostFeedResponse>>
}
