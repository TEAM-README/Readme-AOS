package com.readme.android.domain.entity.request

import com.readme.android.domain.entity.BookInfo

data class DomainPostFeedRequest(
    val categoryName: String,
    val impressiveSentence: String,
    val feeling: String,
    val bookInfo: BookInfo
)
