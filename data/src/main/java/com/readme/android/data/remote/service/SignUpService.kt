package com.readme.android.data.remote.service

import com.readme.android.data.remote.calladapter.NetworkState
import com.readme.android.data.remote.model.request.SignUpRequest
import com.readme.android.data.remote.model.response.BaseResponse
import com.readme.android.data.remote.model.response.SignUpResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface SignUpService {

    @POST("user/signup")
    suspend fun postSignUp(
        @Body body : SignUpRequest
    ):NetworkState<BaseResponse<SignUpResponse>>
}
