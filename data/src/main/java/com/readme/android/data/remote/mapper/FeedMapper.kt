package com.readme.android.data.remote.mapper

import com.readme.android.data.local.datasource.LocalPreferenceUserDataSource
import com.readme.android.data.remote.model.response.Feed
import com.readme.android.domain.entity.FeedInfo
import javax.inject.Inject

class FeedMapper @Inject constructor(
    val dataSource: LocalPreferenceUserDataSource
) {
    fun toFeedInfo(feed: Feed): FeedInfo {
        return FeedInfo(
            id = feed.id,
            category = feed.categoryName,
            title = feed.book?.title ?: throw IllegalStateException("제목은 null 일 수 없습니다"),
            impressiveSentence = feed.sentence,
            takeaway = feed.feeling,
            nickname = feed.user?.nickname
                ?: throw IllegalStateException("nickname 은 null 일 수 없습니다"),
            date = feed.createdAt,
            isMyFeed = feed.user.id == dataSource.getUserId()
        )
    }
}