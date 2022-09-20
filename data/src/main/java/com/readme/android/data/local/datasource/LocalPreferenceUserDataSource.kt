package com.readme.android.data.local.datasource

interface LocalPreferenceUserDataSource {
    fun getAccessToken(): String

    fun saveAccessToken(accessToken: String)

    fun getIsFirstVisit(): Boolean

    fun setIsFirstVisit(isFirstVisit: Boolean)

    fun saveUserNickname(userNickname: String)

    fun getUserNickname(): String

    fun getUserId(): Int

    fun saveUserId(userId: Int)

    fun removeAccessToken()

    fun removeUserNickname()

    fun clearUserInfo()
}
