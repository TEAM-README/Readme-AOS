package com.readme.android.data.remote.model.response


import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("accessToken")
    val accessToken: String?,
    @SerializedName("isNewUser")
    val isNewUser: Boolean
)



