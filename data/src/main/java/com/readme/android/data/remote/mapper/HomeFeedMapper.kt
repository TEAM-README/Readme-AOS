package com.readme.android.data.remote.mapper

import com.readme.android.data.remote.model.response.Feed
import com.readme.android.domain.entity.FeedInfo
import javax.inject.Inject

class HomeFeedMapper @Inject constructor() {
    fun toHomeFeedInfo(homeFeed: Feed): FeedInfo {
        return FeedInfo(
            id = homeFeed.id,
            category = homeFeed.categoryName,
            title = homeFeed.book?.title ?: throw IllegalStateException("제목은 null 일 수 없습니다"),
            impressiveSentence = homeFeed.sentence,
            takeaway = homeFeed.feeling,
            nickname = homeFeed.user?.nickname ?: throw IllegalStateException("제목은 null 일 수 없습니다"),
            date = homeFeed.createdAt,
            isMyFeed = homeFeed.user.id == -1 //TODO : SharedPreference에서 userId 값 가져오기
        )
    }
}