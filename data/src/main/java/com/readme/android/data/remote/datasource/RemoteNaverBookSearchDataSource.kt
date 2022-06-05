package com.readme.android.data.remote.datasource

import com.readme.android.data.remote.calladapter.NetworkState
import com.readme.android.data.remote.model.response.NaverBookSearchResponse
import com.readme.android.domain.entity.BookInfo

interface RemoteNaverBookSearchDataSource {

    suspend fun getNaverBookSearchList(query: String, display: Int, start: Int): NetworkState<NaverBookSearchResponse>
}
