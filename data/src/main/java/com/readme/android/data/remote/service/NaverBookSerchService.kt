package com.readme.android.data.remote.service

import com.readme.android.data.remote.calladapter.NetworkState
import com.readme.android.data.remote.model.response.NaverBookSearchResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NaverBookSearchService {

    @GET("book.json")
    suspend fun getNaverBookSearchList(
        @Query("query") query: String,
        @Query("display") display: Int,
        @Query("start") start: Int
    ): NetworkState<NaverBookSearchResponse>
}
