package com.readme.android.data.remote.service

import com.readme.android.data.remote.calladapter.NetworkState
import com.readme.android.data.remote.model.response.BaseResponse
import com.readme.android.data.remote.model.response.CheckDuplicateNickNameResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface CheckDuplicateNickNameService {

    @GET("user/nickname")
    suspend fun checkDuplicateNickName(
        @Query("query") nickName :String
    ):NetworkState<BaseResponse<CheckDuplicateNickNameResponse>>
}
