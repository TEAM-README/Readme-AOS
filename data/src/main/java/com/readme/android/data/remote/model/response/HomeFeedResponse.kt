package com.readme.android.data.remote.model.response


import com.google.gson.annotations.SerializedName
import com.readme.android.domain.entity.Feed

data class HomeFeedResponse(
    @SerializedName("filters")
    val filters: List<String>,
    @SerializedName("feeds")
    val feeds: List<HomeFeed>
)

data class HomeFeed(
    @SerializedName("id")
    val id: Int,
    @SerializedName("categoryName")
    val categoryName: String,
    @SerializedName("sentence")
    val sentence: String,
    @SerializedName("feeling")
    val feeling: String,
    @SerializedName("reportedCount")
    val reportedCount: Int,
    @SerializedName("createdAt")
    val createdAt: String,
    @SerializedName("isDeleted")
    val isDeleted: Boolean,
    @SerializedName("book")
    val book: Book?,
    @SerializedName("user")
    val user: User?
){
    fun toHomeFeed(): Feed {
        return Feed(
            id = this.id,
            category = this.categoryName,
            title = this.book?.title ?: "null",
            impressiveSentence = this.sentence,
            takeaway = this.feeling,
            nickname = this.user?.nickname ?: "null",
            date = this.createdAt,
            isMyFeed = false
        )
    }
}
