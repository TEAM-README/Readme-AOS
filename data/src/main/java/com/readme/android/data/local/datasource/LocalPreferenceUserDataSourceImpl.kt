package com.readme.android.data.local.datasource

import android.content.SharedPreferences
import javax.inject.Inject

class LocalPreferenceUserDataSourceImpl @Inject constructor(
    private val localPreferences: SharedPreferences
) : LocalPreferenceUserDataSource {
    override fun getAccessToken(): String =
        localPreferences.getString(READ_ME_ACCESS_TOKEN, "") ?: ""

    override fun saveAccessToken(accessToken: String) {
        localPreferences.edit().putString(READ_ME_ACCESS_TOKEN, accessToken).apply()
    }

    override fun getUserNickname(): String =
        localPreferences.getString(USER_NICKNAME,"") ?: ""

    override fun saveUserNickname(userNickname: String) {
        localPreferences.edit().putString(USER_NICKNAME, userNickname).apply()
    }

    override fun removeAccessToken() {
        localPreferences.edit().remove(READ_ME_ACCESS_TOKEN).apply()
    }

    override fun removeUserNickname() {
        localPreferences.edit().remove(USER_NICKNAME).apply()
    }


    companion object {
        const val READ_ME_ACCESS_TOKEN = "READ_ME_ACCESS_TOKEN"
        const val USER_NICKNAME = "USER_NICKNAME"
    }
}

