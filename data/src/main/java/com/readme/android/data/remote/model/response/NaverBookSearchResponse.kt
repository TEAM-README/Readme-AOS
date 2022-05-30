package com.readme.android.data.remote.model.response


import com.google.gson.annotations.SerializedName

data class NaverBookSearchResponse(
    @SerializedName("display")
    val display: Int,
    @SerializedName("items")
    val items: List<Item>,
    @SerializedName("lastBuildDate")
    val lastBuildDate: String,
    @SerializedName("start")
    val start: Int,
    @SerializedName("total")
    val total: Int
)

data class Item(
    @SerializedName("author")
    val author: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("discount")
    val discount: String,
    @SerializedName("image")
    val image: String,
    @SerializedName("isbn")
    val isbn: String,
    @SerializedName("link")
    val link: String,
    @SerializedName("price")
    val price: String,
    @SerializedName("pubdate")
    val pubdate: String,
    @SerializedName("publisher")
    val publisher: String,
    @SerializedName("title")
    val title: String
)

