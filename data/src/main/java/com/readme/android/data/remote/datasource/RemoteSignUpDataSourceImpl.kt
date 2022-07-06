package com.readme.android.data.remote.datasource

import com.readme.android.data.remote.calladapter.NetworkState
import com.readme.android.data.remote.model.request.SignUpRequest
import com.readme.android.data.remote.model.response.BaseResponse
import com.readme.android.data.remote.model.response.CheckDuplicateNickNameResponse
import com.readme.android.data.remote.model.response.SignUpResponse
import com.readme.android.data.remote.service.CheckDuplicateNickNameService
import com.readme.android.data.remote.service.SignUpService
import javax.inject.Inject

class RemoteSignUpDataSourceImpl @Inject constructor(
    private val checkDuplicateNickNameService: CheckDuplicateNickNameService,
    private val signUpService: SignUpService
) : RemoteSignUpDataSource {
    override suspend fun checkDuplicateNickName(nickName: String): NetworkState<BaseResponse<CheckDuplicateNickNameResponse>> =
        checkDuplicateNickNameService.checkDuplicateNickName(nickName)

    override suspend fun postSignUp(signUpRequest: SignUpRequest): NetworkState<BaseResponse<SignUpResponse>> =
        signUpService.postSignUp(signUpRequest)

}
