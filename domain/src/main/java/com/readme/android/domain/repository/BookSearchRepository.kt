package com.readme.android.domain.repository

import com.readme.android.domain.entity.BookInfo

interface BookSearchRepository {

    suspend fun getBookSearchList(query: String, display: Int, start: Int) : Result<List<BookInfo>>

    suspend fun getRecentReadList(): Result<List<BookInfo>>
}
