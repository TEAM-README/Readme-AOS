package com.readme.android.navigator

import android.content.Context
import com.readme.android.MainActivity
import com.readme.android.auth.ui.LoginActivity
import com.readme.android.core_ui.ext.navigateActivity
import com.readme.android.set_nick_name.SetNickNameActivity
import com.readme.android.write_feed.writefeed.FeedWriteActivity
import javax.inject.Inject

internal class MainNavigatorImpl @Inject constructor() : MainNavigator {
    override fun openMain(context: Context) {
        context.navigateActivity<MainActivity>()
    }

    override fun openSetNickName(
        context: Context,
        socialToken: Pair<String, String>,
        platform: Pair<String, String>
    ) {
        context.navigateActivity<SetNickNameActivity>(socialToken, platform)
    }

    override fun openLogin(context: Context) {
        context.navigateActivity<LoginActivity>()
    }

    override fun feedWriteLogin(
        context: Context,
        title: Pair<String, String>,
        author: Pair<String, String>,
        image: Pair<String, String>,
        isbn: Pair<String, String>,
        subIsbn: Pair<String, String>
    ) {
        context.navigateActivity<FeedWriteActivity>(title, author, image, isbn, subIsbn)
    }

    override fun transactionToHome() {
        MainActivity.transactionToHome()
    }
}
