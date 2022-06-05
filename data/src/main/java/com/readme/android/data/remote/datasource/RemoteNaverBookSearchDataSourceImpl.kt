package com.readme.android.data.remote.datasource

import com.readme.android.data.remote.calladapter.NetworkState
import com.readme.android.data.remote.mapper.NaverBookSearchMapper
import com.readme.android.data.remote.model.response.NaverBookSearchResponse
import com.readme.android.data.remote.service.NaverBookSearchService
import com.readme.android.domain.entity.BookInfo
import javax.inject.Inject

class RemoteNaverBookSearchDataSourceImpl @Inject constructor(
    private val naverBookSearchService: NaverBookSearchService
) : RemoteNaverBookSearchDataSource {
    override suspend fun getNaverBookSearchList(
        query: String,
        display: Int,
        start: Int
    ): NetworkState<NaverBookSearchResponse> = naverBookSearchService.getNaverBookSearchList(query, display, start)
}
