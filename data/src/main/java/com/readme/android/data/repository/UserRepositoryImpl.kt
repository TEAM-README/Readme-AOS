package com.readme.android.data.repository

import com.readme.android.core_data.exception.RetrofitFailureStateException
import com.readme.android.data.local.datasource.LocalPreferenceUserDataSource
import com.readme.android.data.remote.calladapter.NetworkState
import com.readme.android.data.remote.datasource.RemoteUserDataSource
import com.readme.android.data.remote.mapper.FeedMapper
import com.readme.android.domain.entity.MyPageInfo
import com.readme.android.domain.entity.MyPageUser
import com.readme.android.domain.repository.UserRepository
import timber.log.Timber
import java.net.HttpURLConnection
import java.security.cert.CertificateException
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val remoteUserDataSource: RemoteUserDataSource,
    private val localPreferenceUserDataSource: LocalPreferenceUserDataSource,
    private val feedMapper: FeedMapper
) : UserRepository {
    override suspend fun getMyPageInfo(): Result<MyPageInfo> {
        when (val response = remoteUserDataSource.getMyFeeds()) {
            is NetworkState.Success -> return Result.success(
                MyPageInfo(
                    myPageUser = MyPageUser(response.body.data.nickname, response.body.data.count),
                    feedList = response.body.data.feeds.map {
                        feedMapper.toFeedInfo(it)
                    }
                )
            )
            is NetworkState.Failure ->
                if (response.code == HttpURLConnection.HTTP_UNAUTHORIZED) throw CertificateException(
                    FeedRepositoryImpl.TOKEN_EXPIRED
                )
                else return Result.failure(
                    RetrofitFailureStateException(response.error, response.code)
                )

            is NetworkState.NetworkError -> Timber.d(response.error)
            is NetworkState.UnknownError -> Timber.d(response.t)
        }
        return Result.failure(IllegalStateException(FeedRepositoryImpl.UNKNOWN_ERROR))
    }

    override suspend fun deleteUser(): Result<String> {
        when (val response = remoteUserDataSource.deleteUser()) {
            is NetworkState.Success -> return Result.success(response.body.message)
            is NetworkState.Failure ->
                if (response.code == HttpURLConnection.HTTP_UNAUTHORIZED) throw CertificateException(
                    FeedRepositoryImpl.TOKEN_EXPIRED
                )
                else return Result.failure(
                    RetrofitFailureStateException(response.error, response.code)
                )
            is NetworkState.NetworkError -> Timber.d(response.error)
            is NetworkState.UnknownError -> Timber.d(response.t)
        }
        return Result.failure(IllegalStateException(FeedRepositoryImpl.UNKNOWN_ERROR))
    }

    override fun clearUserInfo() {
        localPreferenceUserDataSource.clearUserInfo()
    }
}
