package com.readme.android.data.remote.service

import com.readme.android.data.remote.calladapter.NetworkState
import com.readme.android.data.remote.model.request.PostFeedRequest
import com.readme.android.data.remote.model.response.BaseResponse
import com.readme.android.data.remote.model.response.PostFeedResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface PostFeedService {

    @POST("feed")
    suspend fun postFeed(
        @Body body: PostFeedRequest
    ): NetworkState<BaseResponse<PostFeedResponse>>
}
