package com.readme.android.data.remote.service

import com.readme.android.data.remote.calladapter.NetworkState
import com.readme.android.data.remote.model.response.BaseResponse
import com.readme.android.data.remote.model.response.HomeFeedResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface FeedService {

    @GET("feed?")
    suspend fun getHomeFeedList(
        @Query("filters") filters: String
    ): NetworkState<BaseResponse<HomeFeedResponse>>
}