package com.readme.android.data.remote.mapper

import com.readme.android.data.remote.model.response.HomeFeed
import com.readme.android.domain.entity.response.HomeFeedInfo
import javax.inject.Inject

class HomeFeedMapper @Inject constructor(
    private val naverBookSearchMapper: NaverBookSearchMapper,
    private val userMapper: UserMapper
) {
    fun toHomeFeed(homeFeed: HomeFeed): HomeFeedInfo {

        return HomeFeedInfo(
            id = homeFeed.id,
            categoryName = homeFeed.categoryName,
            sentence = homeFeed.sentence,
            feeling = homeFeed.feeling,
            reportedCount = homeFeed.reportedCount,
            createdAt = homeFeed.createdAt,
            book = naverBookSearchMapper.toBookInfo(homeFeed.book),
            user = userMapper.toUserInfo(homeFeed.user)
        )
    }
}