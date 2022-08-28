package com.readme.android.data.remote.mapper

import com.readme.android.data.local.datasource.LocalPreferenceUserDataSource
import com.readme.android.data.remote.model.response.Feed
import com.readme.android.domain.entity.FeedInfo
import javax.inject.Inject

class HomeFeedMapper @Inject constructor(
    val dataSource: LocalPreferenceUserDataSource
) {
    fun toHomeFeedInfo(homeFeed: Feed): FeedInfo {
        return FeedInfo(
            id = homeFeed.id,
            category = homeFeed.categoryName,
            title = homeFeed.book?.title ?: throw IllegalStateException("제목은 null 일 수 없습니다"),
            impressiveSentence = homeFeed.sentence,
            takeaway = homeFeed.feeling,
            nickname = homeFeed.user?.nickname
                ?: throw IllegalStateException("nickname 은 null 일 수 없습니다"),
            date = homeFeed.createdAt,
            isMyFeed = homeFeed.user.id == dataSource.getUserId()
        )
    }
}