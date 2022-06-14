package com.readme.android.data.remote.mapper

import com.readme.android.data.remote.model.response.Item
import com.readme.android.data.remote.model.response.NaverBookSearchResponse
import com.readme.android.domain.entity.BookInfo
import javax.inject.Inject

class NaverBookSearchMapper @Inject constructor() {

    fun toBookInfo(bookItem: Item):BookInfo = BookInfo(
        title = bookItem.title,
        author = bookItem.author,
        image = bookItem.image
    )
}
