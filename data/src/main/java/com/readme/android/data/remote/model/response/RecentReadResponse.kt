package com.readme.android.data.remote.model.response


import com.google.gson.annotations.SerializedName

data class RecentReadResponse(
    @SerializedName("books")
    val books: List<Book>
)
