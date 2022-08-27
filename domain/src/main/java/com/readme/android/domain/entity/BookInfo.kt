package com.readme.android.domain.entity

import java.io.Serializable

data class BookInfo(
    val title: String,
    val author: String?,
    val image: String,
    val isbn: String,
    val subIsbn: String
) : Serializable
