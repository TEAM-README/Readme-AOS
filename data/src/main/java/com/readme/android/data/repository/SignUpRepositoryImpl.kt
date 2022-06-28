package com.readme.android.data.repository

import com.readme.android.core_data.exception.RetrofitFailureStateException
import com.readme.android.data.local.datasource.LocalPreferenceUserDataSource
import com.readme.android.data.remote.calladapter.NetworkState
import com.readme.android.data.remote.datasource.RemoteSignUpDataSource
import com.readme.android.data.remote.model.request.SignUpRequest
import com.readme.android.domain.entity.request.DomainSignUpRequest
import com.readme.android.domain.entity.response.DomainLoginResponse
import com.readme.android.domain.repository.SignUpRepository
import timber.log.Timber
import javax.inject.Inject

class SignUpRepositoryImpl @Inject constructor(
    private val signUpDataSource: RemoteSignUpDataSource,
    private val localPreferenceUserDataSource: LocalPreferenceUserDataSource
) : SignUpRepository {
    override suspend fun checkDuplicateNickName(nickName: String): Result<Boolean> {
        val response = signUpDataSource.checkDuplicateNickName(nickName)

        when (response) {
            is NetworkState.Success -> return Result.success(
                response.body.data.available ?: throw IllegalStateException()
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

    override suspend fun postSignUp(signUpRequest: DomainSignUpRequest): Result<String> {
        val response = signUpDataSource.postSignUp(
            SignUpRequest(
                platform = signUpRequest.platform,
                socialToken = signUpRequest.socialToken,
                nickname = signUpRequest.nickname
            )
        )

        when (response) {
            is NetworkState.Success -> return Result.success(response.body.data.accessToken)
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

    override fun saveAccessToken(accessToken: String) {
        localPreferenceUserDataSource.saveAccessToken(accessToken)
    }

    override fun saveUserNickname(userNickname: String) {
        localPreferenceUserDataSource.saveUserNickname(userNickname)
    }


}


