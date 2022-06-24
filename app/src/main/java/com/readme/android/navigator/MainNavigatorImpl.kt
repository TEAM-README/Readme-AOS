package com.readme.android.navigator

import android.content.Context
import com.readme.android.MainActivity
import javax.inject.Inject
import com.readme.android.core_ui.ext.startActivity

internal class MainNavigatorImpl @Inject constructor() : MainNavigator {
    override fun openMain(context: Context) {
        context.startActivity<MainActivity>()
    }
}
