package com.readme.android.data.remote.datasource

import com.readme.android.data.remote.calladapter.NetworkState
import com.readme.android.data.remote.model.request.LoginRequest
import com.readme.android.data.remote.model.response.BaseResponse
import com.readme.android.data.remote.model.response.LoginResponse
import com.readme.android.data.remote.service.LoginService
import javax.inject.Inject

class RemoteLoginDataSourceImpl @Inject constructor(
    private val loginService: LoginService
) : RemoteLoginDataSource {
    override suspend fun postLogin(loginRequest: LoginRequest): NetworkState<BaseResponse<LoginResponse>> =
        loginService.postLogin(loginRequest)
}
