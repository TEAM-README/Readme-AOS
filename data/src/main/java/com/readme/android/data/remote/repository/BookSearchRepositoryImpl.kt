package com.readme.android.data.remote.repository

import com.readme.android.data.remote.calladapter.NetworkState
import com.readme.android.data.remote.calladapter.RetrofitFailureStateException
import com.readme.android.data.remote.datasource.RemoteBookSearchDataSource
import com.readme.android.data.remote.mapper.NaverBookSearchMapper
import com.readme.android.domain.entity.BookInfo
import com.readme.android.domain.repository.BookSearchRepository
import timber.log.Timber
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
        when (val bookSearchList = remoteBookSearchDataSource.getBookSearchList(query, display, start)) {
            is NetworkState.Success -> return Result.success(bookSearchList.body.items.map { naverBookSearchMapper.toBookInfo(it) })
            is NetworkState.Failure -> return Result.failure(RetrofitFailureStateException(bookSearchList.error,bookSearchList.code))
            is NetworkState.NetworkError -> Timber.tag("${this.javaClass.name}_getBookSearchList").d(bookSearchList.error)
            is NetworkState.UnknownError -> Timber.tag("${this.javaClass.name}_getBookSearchList").d(bookSearchList.t)
        }
        return Result.failure(IllegalStateException("NetworkError or UnKnownError please check timber"))
    }
}
