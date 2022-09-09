package com.readme.android.data.remote.datasource

import com.readme.android.data.remote.calladapter.NetworkState
import com.readme.android.data.remote.model.response.BaseResponse
import com.readme.android.data.remote.model.response.MyFeedResponse
import com.readme.android.data.remote.model.response.NoDataResponse

interface RemoteUserDataSource {
    suspend fun getMyFeeds(): NetworkState<BaseResponse<MyFeedResponse>>

    suspend fun deleteUser(): NetworkState<NoDataResponse>
}
