package com.readme.android.data.repository

import com.readme.android.core_data.exception.RetrofitFailureStateException
import com.readme.android.data.remote.calladapter.NetworkState
import com.readme.android.data.remote.datasource.RemoteHomeFeedDataSource
import com.readme.android.data.remote.mapper.HomeFeedMapper
import com.readme.android.domain.entity.response.DomainHomeFeedResponse
import com.readme.android.domain.repository.FeedRepository
import timber.log.Timber
import javax.inject.Inject

class FeedRepositoryImpl @Inject constructor(
    private val remoteHomeFeedDataSource: RemoteHomeFeedDataSource,
    private val homeFeedMapper: HomeFeedMapper
) : FeedRepository {
    override suspend fun getHomeFeed(filter: String): Result<DomainHomeFeedResponse> {
        when (val response = remoteHomeFeedDataSource.getHomeFeedList(filter)) {
            is NetworkState.Success -> return Result.success(
                DomainHomeFeedResponse(
                    filters = response.body.data.filters,
                    feeds = response.body.data.feeds.map {
                        homeFeedMapper.toHomeFeed(it)
                    }
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