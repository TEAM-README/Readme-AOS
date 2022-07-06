package com.readme.android.data.local.datasource

interface LocalPreferenceUserDataSource {
    fun getAccessToken(): String

    fun saveAccessToken(accessToken: String)

    fun saveUserNickname(userNickname: String)

    fun getUserNickname(): String

    fun removeAccessToken()

    fun removeUserNickname()
}
