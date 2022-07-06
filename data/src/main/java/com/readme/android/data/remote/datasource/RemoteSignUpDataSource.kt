package com.readme.android.data.remote.datasource

import com.readme.android.data.remote.calladapter.NetworkState
import com.readme.android.data.remote.model.request.SignUpRequest
import com.readme.android.data.remote.model.response.BaseResponse
import com.readme.android.data.remote.model.response.CheckDuplicateNickNameResponse
import com.readme.android.data.remote.model.response.SignUpResponse

interface RemoteSignUpDataSource {

    suspend fun checkDuplicateNickName(nickName: String): NetworkState<BaseResponse<CheckDuplicateNickNameResponse>>

    suspend fun postSignUp(signUpRequest: SignUpRequest): NetworkState<BaseResponse<SignUpResponse>>
}
