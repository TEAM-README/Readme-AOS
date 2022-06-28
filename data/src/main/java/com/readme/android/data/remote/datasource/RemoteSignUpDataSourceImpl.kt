package com.readme.android.data.remote.datasource

import com.readme.android.data.remote.calladapter.NetworkState
import com.readme.android.data.remote.model.request.LoginRequest
import com.readme.android.data.remote.model.response.BaseResponse
import com.readme.android.data.remote.model.response.CheckDuplicateNickNameResponse
import com.readme.android.data.remote.model.response.LoginResponse
import com.readme.android.data.remote.service.CheckDuplicateNickNameService
import javax.inject.Inject

class RemoteSignUpDataSourceImpl @Inject constructor(
    private val checkDuplicateNickNameService: CheckDuplicateNickNameService
) : RemoteSignUpDataSource {
    override suspend fun checkDuplicateNickName(nickName: String): NetworkState<BaseResponse<CheckDuplicateNickNameResponse>> =
        checkDuplicateNickNameService.checkDuplicateNickName(nickName)
}
