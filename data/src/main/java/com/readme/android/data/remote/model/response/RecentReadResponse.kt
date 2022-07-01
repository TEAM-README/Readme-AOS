package com.readme.android.data.remote.model.response


import com.google.gson.annotations.SerializedName

data class RecentReadResponse(
    @SerializedName("books")
    val books: List<Book>
)

data class Book(
    @SerializedName("author")
    val author: String,
    @SerializedName("image")
    val image: String,
    @SerializedName("isbn")
    val isbn: String,
    @SerializedName("subIsbn")
    val subIsbn: String,
    @SerializedName("title")
    val title: String
)

