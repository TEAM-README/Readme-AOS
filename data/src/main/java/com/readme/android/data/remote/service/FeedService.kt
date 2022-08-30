package com.readme.android.data.remote.service

import com.readme.android.data.remote.calladapter.NetworkState
import com.readme.android.data.remote.model.request.PostFeedRequest
import com.readme.android.data.remote.model.response.BaseResponse
import com.readme.android.data.remote.model.response.DetailFeedResponse
import com.readme.android.data.remote.model.response.HomeFeedResponse
import com.readme.android.data.remote.model.response.PostFeedResponse
import retrofit2.http.*

interface FeedService {

    @POST("feed")
    suspend fun postFeed(
        @Body body: PostFeedRequest
    ): NetworkState<BaseResponse<PostFeedResponse>>

    @GET("feed")
    suspend fun getHomeFeedList(
        @Query("filters") filters: String
    ): NetworkState<BaseResponse<HomeFeedResponse>>

    @GET("/feed/{feedId}")
    suspend fun getDetailFeed(
        @Path("feedId") feedId: Int
    ): NetworkState<BaseResponse<DetailFeedResponse>>
}