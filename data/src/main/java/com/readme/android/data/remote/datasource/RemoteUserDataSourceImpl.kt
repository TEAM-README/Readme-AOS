package com.readme.android.data.remote.datasource

import com.readme.android.data.remote.calladapter.NetworkState
import com.readme.android.data.remote.model.response.BaseResponse
import com.readme.android.data.remote.model.response.MyFeedResponse
import com.readme.android.data.remote.model.response.NoDataResponse
import com.readme.android.data.remote.service.UserService
import javax.inject.Inject

class RemoteUserDataSourceImpl @Inject constructor(
    private val userService: UserService
) : RemoteUserDataSource {
    override suspend fun getMyFeeds(): NetworkState<BaseResponse<MyFeedResponse>> =
        userService.getMyFeeds()

    override suspend fun deleteUser(): NetworkState<NoDataResponse> =
        userService.deleteUser()
}
