package com.readme.android.data.repository

import com.readme.android.core_data.exception.RetrofitFailureStateException
import com.readme.android.data.local.datasource.LocalPreferenceUserDataSource
import com.readme.android.data.remote.calladapter.NetworkState
import com.readme.android.data.remote.datasource.RemotePostFeedDataSource
import com.readme.android.data.remote.model.request.PostFeedRequest
import com.readme.android.domain.entity.request.DomainPostFeedRequest
import com.readme.android.domain.entity.response.DomainHomeFeedResponse
import com.readme.android.domain.repository.FeedWriteRepository
import timber.log.Timber
import java.security.cert.CertificateException
import javax.inject.Inject

class FeedWriteRepositoryImpl @Inject constructor(
    private val localPreferenceUserDataSource: LocalPreferenceUserDataSource,
    private val remotePostFeedDataSource: RemotePostFeedDataSource
) : FeedWriteRepository {
    override fun getUserNickName(): String =
        localPreferenceUserDataSource.getUserNickname()

    override suspend fun postFeed(postFeedRequest: DomainPostFeedRequest): Result<Boolean> {
        when (
            val response = remotePostFeedDataSource.postFeed(
                PostFeedRequest(
                    categoryName = postFeedRequest.categoryName,
                    impressiveSentence = postFeedRequest.impressiveSentence,
                    feeling = postFeedRequest.feeling,
                    bookInfo = postFeedRequest.bookInfo
                )
            )
        ) {
            is NetworkState.Success -> return Result.success(response.body.success)
            is NetworkState.Failure ->
                if (response.code == 401) throw CertificateException("토큰 만료 오류")
                else return Result.failure(
                    RetrofitFailureStateException(response.error, response.code)
                )

            is NetworkState.NetworkError -> Timber.tag("${this.javaClass.name}_postFeed")
                .d(response.error)
            is NetworkState.UnknownError -> Timber.tag("${this.javaClass.name}_postFeed")
                .d(response.t)
        }
        return Result.failure(IllegalStateException("NetworkError or UnKnownError please check timber"))
    }
}
