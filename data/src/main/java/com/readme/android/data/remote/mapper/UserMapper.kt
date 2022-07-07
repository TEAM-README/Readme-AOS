package com.readme.android.data.remote.mapper

import com.readme.android.data.remote.model.response.User
import com.readme.android.domain.entity.UserInfo
import javax.inject.Inject

class UserMapper @Inject constructor() {

    fun toUserInfo(user: User): UserInfo =
        UserInfo(
            id = user.id,
            nickname = user.nickname
        )

}