package com.readme.android.data.remote.model.response

import com.google.gson.annotations.SerializedName
import com.readme.android.domain.entity.BookInfo

data class Book(
    @SerializedName("author")
    val author: String?,
    @SerializedName("image")
    val image: String,
    @SerializedName("isbn")
    val isbn: String,
    @SerializedName("subIsbn")
    val subIsbn: String,
    @SerializedName("title")
    val title: String
){
    fun toBookInfo(): BookInfo =
        BookInfo(
            title = this.title,
            author = this.author,
            image = this.image,
            isbn = this.isbn,
            subIsbn = this.subIsbn
        )
}
