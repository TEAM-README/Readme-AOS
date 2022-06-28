package com.readme.android.data.remote.datasource

import com.readme.android.data.remote.calladapter.NetworkState
import com.readme.android.data.remote.model.response.BaseResponse
import com.readme.android.data.remote.model.response.CheckDuplicateNickNameResponse

interface RemoteSignUpDataSource {

    suspend fun checkDuplicateNickName(nickName: String): NetworkState<BaseResponse<CheckDuplicateNickNameResponse>>
}
