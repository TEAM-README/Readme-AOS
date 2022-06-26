package com.readme.android.navigator

import android.content.Context

interface MainNavigator {
    /** MainActivity로 이동 */
    fun openMain(context: Context)

    /** SetNickNameActivity로 이동 */
    fun openSetNickName(
        context: Context,
        socialToken: Pair<String, String>,
        platform: Pair<String, String>
    )
}
