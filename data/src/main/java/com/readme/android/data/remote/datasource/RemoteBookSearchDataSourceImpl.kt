package com.readme.android.data.remote.datasource

import com.readme.android.data.remote.calladapter.NetworkState
import com.readme.android.data.remote.model.response.NaverBookSearchResponse
import com.readme.android.data.remote.service.NaverBookSearchService
import javax.inject.Inject

class RemoteBookSearchDataSourceImpl @Inject constructor(
    private val naverBookSearchService: NaverBookSearchService
) : RemoteBookSearchDataSource {
    override suspend fun getBookSearchList(
        query: String,
        display: Int,
        start: Int
    ): NetworkState<NaverBookSearchResponse> = naverBookSearchService.getNaverBookSearchList(query, display, start)
}
