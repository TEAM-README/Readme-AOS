package com.readme.android.data.repository

import com.readme.android.core_data.exception.RetrofitFailureStateException
import com.readme.android.data.remote.calladapter.NetworkState
import com.readme.android.data.remote.datasource.RemoteFeedDataSource
import com.readme.android.domain.entity.response.DomainDetailFeedResponse
import com.readme.android.domain.entity.response.DomainHomeFeedResponse
import com.readme.android.domain.entity.response.DomainNoDataResponse
import com.readme.android.domain.repository.FeedRepository
import timber.log.Timber.tag
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
                    feedListInfo = response.body.data.feeds.map {
                        it.toFeedInfo()
                    }
                )
            )
            is NetworkState.Failure ->
                if (response.code == 401) throw CertificateException("토큰 만료 오류")
                else return Result.failure(
                    RetrofitFailureStateException(response.error, response.code)
                )

            is NetworkState.NetworkError -> tag("${this.javaClass.name}_getHomeFeed")
                .d(response.error)
            is NetworkState.UnknownError -> tag("${this.javaClass.name}_getHomeFeed")
                .d(response.t)
        }
        return Result.failure(IllegalStateException(UNKNOWN_ERROR))
    }

    override suspend fun getDetailFeed(feedId: Int): Result<DomainDetailFeedResponse> {
        when (val response = remoteFeedDataSource.getDetailFeed(feedId)) {
            is NetworkState.Success -> return Result.success(
                DomainDetailFeedResponse(
                    response.body.data.feed.toFeedInfo(),
                    response.body.data.feed.book?.toBookInfo()
                        ?: throw IllegalStateException("book data cannot be null")
                )
            )
            is NetworkState.Failure ->
                if (response.code == 401) throw CertificateException("토큰 만료 오류")
                else return Result.failure(
                    RetrofitFailureStateException(response.error, response.code)
                )
            is NetworkState.NetworkError -> tag("${this.javaClass.name}_getHomeFeed")
                .d(response.error)
            is NetworkState.UnknownError -> tag("${this.javaClass.name}_getHomeFeed")
                .d(response.t)
        }
        return Result.failure(IllegalStateException(UNKNOWN_ERROR))
    }

    override suspend fun deleteFeed(feedId: Int): Result<DomainNoDataResponse> {
        when (val response = remoteFeedDataSource.deleteFeed(feedId)) {
            is NetworkState.Success -> Result.success(response)
            is NetworkState.Failure ->
                if (response.code == 401) throw CertificateException("토큰 만료 오류")
                else return Result.failure(
                    RetrofitFailureStateException(response.error, response.code)
                )
            is NetworkState.NetworkError -> tag("deleteFeed").d(response.error)
            is NetworkState.UnknownError -> tag("deleteFeed").d(response.t)
        }
        return Result.failure(IllegalStateException(UNKNOWN_ERROR))
    }

    companion object {
        const val UNKNOWN_ERROR = "NetworkError or UnKnownError please check timber"
    }
}


