package com.readme.android.data.repository

import com.readme.android.core_data.exception.RetrofitFailureStateException
import com.readme.android.data.remote.calladapter.NetworkState
import com.readme.android.data.remote.datasource.RemoteFeedDataSource
import com.readme.android.domain.entity.response.DomainDetailFeedResponse
import com.readme.android.domain.entity.response.DomainHomeFeedResponse
import com.readme.android.domain.repository.FeedRepository
import timber.log.Timber
import java.security.cert.CertificateException
import javax.inject.Inject

class FeedRepositoryImpl @Inject constructor(
    private val remoteFeedDataSource: RemoteFeedDataSource,
) : FeedRepository {
    override suspend fun getHomeFeed(filters: String): Result<DomainHomeFeedResponse> {
        when (val response = remoteFeedDataSource.getHomeFeedList(filters)) {
            is NetworkState.Success -> return Result.success(
                DomainHomeFeedResponse(
                    filters = response.body.data.filters,
                    feedInfos = response.body.data.feeds.map {
                        it.toFeedInfo()
                    }
                )
            )
            is NetworkState.Failure ->
                if (response.code == 401) throw CertificateException("토큰 만료 오류")
                else return Result.failure(
                    RetrofitFailureStateException(response.error, response.code)
                )

            is NetworkState.NetworkError -> Timber.tag("${this.javaClass.name}_getHomeFeed")
                .d(response.error)
            is NetworkState.UnknownError -> Timber.tag("${this.javaClass.name}_getHomeFeed")
                .d(response.t)
        }
        return Result.failure(IllegalStateException("NetworkError or UnKnownError please check timber"))
    }

    override suspend fun getDetailFeed(feedId: Int): Result<DomainDetailFeedResponse> {
        when (val response = remoteFeedDataSource.getDetailFeed(feedId)) {
            is NetworkState.Success -> return Result.success(
                DomainDetailFeedResponse(
                    response.body.data.feed.toFeedInfo(),
                    response.body.data.feed.book?.toBookInfo() ?: throw IllegalStateException("book data cannot be null")
                )
            )
            is NetworkState.Failure ->
                if (response.code == 401) throw CertificateException("토큰 만료 오류")
                else return Result.failure(
                    RetrofitFailureStateException(response.error, response.code)
                )
            is NetworkState.NetworkError -> Timber.tag("${this.javaClass.name}_getHomeFeed").d(response.error)
            is NetworkState.UnknownError -> Timber.tag("${this.javaClass.name}_getHomeFeed").d(response.t)
        }
        return Result.failure(IllegalStateException("NetworkError or UnKnownError please check timber"))
    }

}