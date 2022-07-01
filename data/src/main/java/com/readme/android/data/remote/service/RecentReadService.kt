package com.readme.android.data.remote.service

import com.readme.android.data.remote.calladapter.NetworkState
import com.readme.android.data.remote.model.response.BaseResponse
import com.readme.android.data.remote.model.response.RecentReadResponse
import retrofit2.http.GET

interface RecentReadService {

    @GET("feed/recent")
    suspend fun getRecentReadList():NetworkState<BaseResponse<RecentReadResponse>>
}
