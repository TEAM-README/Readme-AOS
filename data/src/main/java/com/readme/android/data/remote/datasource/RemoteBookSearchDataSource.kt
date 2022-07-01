package com.readme.android.data.remote.datasource

import com.readme.android.data.remote.calladapter.NetworkState
import com.readme.android.data.remote.model.response.BaseResponse
import com.readme.android.data.remote.model.response.NaverBookSearchResponse
import com.readme.android.data.remote.model.response.RecentReadResponse
import com.readme.android.domain.entity.BookInfo

interface RemoteBookSearchDataSource {

    suspend fun getBookSearchList(query: String, display: Int, start: Int): NetworkState<NaverBookSearchResponse>

    suspend fun getRecentReadList():NetworkState<BaseResponse<RecentReadResponse>>
}
