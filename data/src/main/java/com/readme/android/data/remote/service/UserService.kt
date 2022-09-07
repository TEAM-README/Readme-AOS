package com.readme.android.data.remote.service

import com.readme.android.data.remote.calladapter.NetworkState
import com.readme.android.data.remote.model.response.BaseResponse
import com.readme.android.data.remote.model.response.MyFeedResponse
import com.readme.android.data.remote.model.response.NoDataResponse
import retrofit2.http.DELETE
import retrofit2.http.GET

interface UserService {
    @GET("user/myFeeds")
    suspend fun getMyFeeds(): NetworkState<BaseResponse<MyFeedResponse>>

    @DELETE
    suspend fun deleteUser(): NetworkState<NoDataResponse>
}
