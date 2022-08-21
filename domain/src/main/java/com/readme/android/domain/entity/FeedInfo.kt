package com.readme.android.domain.entity

data class FeedInfo(
    val id: Int,
    val category: String,
    val title: String,
    val impressiveSentence: String,
    val takeaway: String,
    val nickname: String,
    val date: String,
    val isMyFeed: Boolean
)
