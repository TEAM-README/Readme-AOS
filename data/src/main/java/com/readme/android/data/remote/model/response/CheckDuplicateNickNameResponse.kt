package com.readme.android.data.remote.model.response

import com.google.gson.annotations.SerializedName

data class CheckDuplicateNickNameResponse(
    @SerializedName("available")
    val available: Boolean?
)
