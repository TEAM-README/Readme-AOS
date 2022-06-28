package com.readme.android.data.repository

import com.readme.android.core_data.exception.RetrofitFailureStateException
import com.readme.android.data.local.datasource.LocalPreferenceUserDataSource
import com.readme.android.data.remote.calladapter.NetworkState
import com.readme.android.data.remote.datasource.RemoteLoginDataSource
import com.readme.android.data.remote.model.request.LoginRequest
import com.readme.android.domain.entity.request.DomainLoginRequest
import com.readme.android.domain.entity.response.DomainLoginResponse
import com.readme.android.domain.repository.LoginRepository
import timber.log.Timber
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val localPreferenceUserDataSource: LocalPreferenceUserDataSource,
    private val remoteLoginDataSource: RemoteLoginDataSource
) : LoginRepository {
    override fun saveAccessToken(accessToken: String) {
        localPreferenceUserDataSource.saveAccessToken(accessToken)
    }

    override fun saveUserNickname(userNickname: String) {
        localPreferenceUserDataSource.saveUserNickname(userNickname)
    }

    override suspend fun postLogin(loginRequest: DomainLoginRequest): Result<DomainLoginResponse> {
        val response = remoteLoginDataSource.postLogin(
            LoginRequest(
                platform = loginRequest.platform,
                socialToken = loginRequest.socialToken
            )
        )

        when (response) {
            is NetworkState.Success -> return Result.success(
                DomainLoginResponse(
                    accessToken = response.body.data.accessToken,
                    isNewUser = response.body.data.isNewUser
                )
            )
            is NetworkState.Failure -> return Result.failure(
                RetrofitFailureStateException(
                    response.error,
                    response.code
                )
            )
            is NetworkState.NetworkError -> Timber.tag("${this.javaClass.name}_postNaverLogin")
                .d(response.error)
            is NetworkState.UnknownError -> Timber.tag("${this.javaClass.name}_postNaverLogin")
                .d(response.t)
        }
        return Result.failure(IllegalStateException("NetworkError or UnKnownError please check timber"))
    }
}
