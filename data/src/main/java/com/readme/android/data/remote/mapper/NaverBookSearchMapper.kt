package com.readme.android.data.remote.mapper

import com.readme.android.data.remote.model.response.Book
import com.readme.android.data.remote.model.response.Item
import com.readme.android.domain.entity.BookInfo
import javax.inject.Inject

class NaverBookSearchMapper @Inject constructor() {

    fun toBookInfo(bookItem: Item): BookInfo {

        val strArr = bookItem.isbn.split(" ").toMutableList()

        if (strArr[0] == "") {
            strArr.add("")
        }

        return BookInfo(
            title = bookItem.title,
            author = bookItem.author,
            image = bookItem.image,
            isbn = strArr[0],
            subIsbn = strArr[1]
        )
    }

    fun toBookInfo(bookItem: Book): BookInfo =
        BookInfo(
            title = bookItem.title,
            author = bookItem.author,
            image = bookItem.image,
            isbn = bookItem.isbn,
            subIsbn = bookItem.subIsbn
        )
}
