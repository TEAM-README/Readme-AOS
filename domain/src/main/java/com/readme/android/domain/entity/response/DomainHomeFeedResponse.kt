package com.readme.android.domain.entity.response

import com.readme.android.domain.entity.BookInfo
import com.readme.android.domain.entity.UserInfo

data class DomainHomeFeedResponse(
    val filters: List<String>,
    val feeds: List<HomeFeedInfo>
)

data class HomeFeedInfo(
    val id: Int,
    val categoryName: String,
    val sentence: String,
    val feeling: String,
    val reportedCount: Int,
    val createdAt: String,
    val book: BookInfo,
    val user: UserInfo
)
