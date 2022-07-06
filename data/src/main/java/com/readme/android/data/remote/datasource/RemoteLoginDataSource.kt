package com.readme.android.data.remote.datasource

import com.readme.android.data.remote.calladapter.NetworkState
import com.readme.android.data.remote.model.request.LoginRequest
import com.readme.android.data.remote.model.response.BaseResponse
import com.readme.android.data.remote.model.response.LoginResponse

interface RemoteLoginDataSource {

    suspend fun postLogin(loginRequest: LoginRequest): NetworkState<BaseResponse<LoginResponse>>
}
