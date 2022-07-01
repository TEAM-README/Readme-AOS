package com.readme.android.data.remote.datasource

import com.readme.android.data.remote.calladapter.NetworkState
import com.readme.android.data.remote.model.response.BaseResponse
import com.readme.android.data.remote.model.response.NaverBookSearchResponse
import com.readme.android.data.remote.model.response.RecentReadResponse
import com.readme.android.data.remote.service.NaverBookSearchService
import com.readme.android.data.remote.service.RecentReadService
import javax.inject.Inject

class RemoteBookSearchDataSourceImpl @Inject constructor(
    private val naverBookSearchService: NaverBookSearchService,
    private val recentReadService: RecentReadService
) : RemoteBookSearchDataSource {
    override suspend fun getBookSearchList(
        query: String,
        display: Int,
        start: Int
    ): NetworkState<NaverBookSearchResponse> = naverBookSearchService.getNaverBookSearchList(query, display, start)

    override suspend fun getRecentReadList(): NetworkState<BaseResponse<RecentReadResponse>> =
        recentReadService.getRecentReadList()
}
