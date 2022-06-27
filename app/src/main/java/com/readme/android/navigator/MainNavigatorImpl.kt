package com.readme.android.navigator

import android.content.Context
import com.readme.android.MainActivity
import com.readme.android.auth.ui.LoginActivity
import javax.inject.Inject
import com.readme.android.core_ui.ext.startActivity
import com.readme.android.set_nick_name.SetNickNameActivity

internal class MainNavigatorImpl @Inject constructor() : MainNavigator {
    override fun openMain(context: Context) {
        context.startActivity<MainActivity>()
    }

    override fun openSetNickName(
        context: Context,
        socialToken: Pair<String, String>,
        platform: Pair<String, String>
    ) {
        context.startActivity<SetNickNameActivity>(socialToken,platform)
    }

    override fun openLogin(context: Context) {
        context.startActivity<LoginActivity>()
    }
}
