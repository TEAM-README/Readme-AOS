package com.readme.android.data.repository

import com.readme.android.core_data.exception.RetrofitFailureStateException
import com.readme.android.data.remote.calladapter.NetworkState
import com.readme.android.data.remote.datasource.RemoteBookSearchDataSource
import com.readme.android.data.remote.mapper.NaverBookSearchMapper
import com.readme.android.domain.entity.BookInfo
import com.readme.android.domain.repository.BookSearchRepository
import timber.log.Timber
import java.security.cert.CertificateException
import javax.inject.Inject

class BookSearchRepositoryImpl @Inject constructor(
    private val remoteBookSearchDataSource: RemoteBookSearchDataSource,
    private val naverBookSearchMapper: NaverBookSearchMapper
) : BookSearchRepository {

    override suspend fun getBookSearchList(
        query: String,
        display: Int,
        start: Int
    ): Result<List<BookInfo>> {
        when (val bookSearchList =
            remoteBookSearchDataSource.getBookSearchList(query, display, start)) {
            is NetworkState.Success -> return Result.success(bookSearchList.body.items.map {
                naverBookSearchMapper.toBookInfo(
                    it
                )
            })
            is NetworkState.Failure -> return Result.failure(
                RetrofitFailureStateException(
                    bookSearchList.error,
                    bookSearchList.code
                )
            )
            is NetworkState.NetworkError -> Timber.tag("${this.javaClass.name}_getBookSearchList")
                .d(bookSearchList.error)
            is NetworkState.UnknownError -> Timber.tag("${this.javaClass.name}_getBookSearchList")
                .d(bookSearchList.t)
        }
        return Result.failure(IllegalStateException("NetworkError or UnKnownError please check timber"))
    }

    override suspend fun getRecentReadList(): Result<List<BookInfo>> {
        when (val recentReadList = remoteBookSearchDataSource.getRecentReadList()) {
            is NetworkState.Success -> return Result.success(recentReadList.body.data.books.map {
                naverBookSearchMapper.toBookInfo(
                    it
                )
            })
            is NetworkState.Failure -> {
                if (recentReadList.code == 401) {
                    throw CertificateException("토큰 만료 오류")
                } else {
                    return Result.failure(
                        RetrofitFailureStateException(
                            recentReadList.error,
                            recentReadList.code
                        )
                    )
                }
            }
            is NetworkState.NetworkError -> Timber.tag("${this.javaClass.name}_getRecentReadList")
                .d(recentReadList.error)
            is NetworkState.UnknownError -> Timber.tag("${this.javaClass.name}_getRecentReadList")
                .d(recentReadList.t)
        }
        return Result.failure(IllegalStateException("NetworkError or UnKnownError please check timber"))
    }
}
