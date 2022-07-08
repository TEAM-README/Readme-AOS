package com.readme.android.data.remote.datasource

import com.readme.android.data.remote.calladapter.NetworkState
import com.readme.android.data.remote.model.response.BaseResponse
import com.readme.android.data.remote.model.response.HomeFeedResponse

interface RemoteHomeFeedDataSource {

    suspend fun getHomeFeedList(
        filters: String
    ): NetworkState<BaseResponse<HomeFeedResponse>>
}