package com.readme.android.data.local.datasource

import android.content.SharedPreferences
import androidx.core.content.edit
import javax.inject.Inject

class LocalPreferenceUserDataSourceImpl @Inject constructor(
    private val localPreferences: SharedPreferences
) : LocalPreferenceUserDataSource {
    override fun getAccessToken(): String =
        localPreferences.getString(READ_ME_ACCESS_TOKEN, "") ?: ""

    override fun saveAccessToken(accessToken: String) {
        localPreferences.edit { putString(READ_ME_ACCESS_TOKEN, accessToken) }
    }

    override fun getIsFirstVisit(): Boolean =
        localPreferences.getBoolean(IS_FIRST_VISIT, true)

    override fun setIsFirstVisit(isFirstVisit: Boolean) {
        localPreferences.edit { putBoolean(IS_FIRST_VISIT, isFirstVisit) }
    }

    override fun getUserNickname(): String =
        localPreferences.getString(USER_NICKNAME, "") ?: ""

    override fun getUserId(): Int =
        localPreferences.getInt(USER_ID, -1)

    override fun saveUserId(userId: Int) {
        localPreferences.edit { putInt(USER_ID, userId) }
    }

    override fun saveUserNickname(userNickname: String) {
        localPreferences.edit { putString(USER_NICKNAME, userNickname) }
    }

    override fun removeAccessToken() {
        localPreferences.edit { remove(READ_ME_ACCESS_TOKEN) }
    }

    override fun removeUserNickname() {
        localPreferences.edit { remove(USER_NICKNAME) }
    }

    override fun removeUserId() {
        localPreferences.edit { remove(USER_ID) }
    }

    override fun clearUserInfo() {
        localPreferences.edit { clear() }
    }

    companion object {
        const val READ_ME_ACCESS_TOKEN = "READ_ME_ACCESS_TOKEN"
        const val USER_NICKNAME = "USER_NICKNAME"
        const val USER_ID = "USER_ID"
        const val IS_FIRST_VISIT = "IS_FIRST_VISIT"
    }
}
