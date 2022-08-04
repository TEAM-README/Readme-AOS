package com.readme.android.data.remote.mapper

import com.readme.android.data.remote.model.response.HomeFeed
import com.readme.android.domain.entity.Feed

class HomeFeedMapper {
    fun toHomeFeed(homeFeed: HomeFeed): Feed {
        return Feed(
            id = homeFeed.id,
            category = homeFeed.categoryName,
            title = homeFeed.book.title,
            impressiveSentence = homeFeed.sentence,
            takeaway = homeFeed.feeling,
            nickname = homeFeed.user.nickname,
            date = homeFeed.createdAt,
            isMyFeed = false
        )
    }
}