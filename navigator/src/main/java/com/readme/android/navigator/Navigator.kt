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

    /** LoginActivity로 이동 */
    fun openLogin(context: Context)

    /** FeedWriteActivity로 이동 */
    fun feedWriteLogin(
        context: Context,
        title: Pair<String, String>,
        author: Pair<String, String>,
        image: Pair<String, String>,
        isbn: Pair<String, String>,
        subIsbn: Pair<String, String>
    )

    /** MainActivity로 이동시 home화면이 나오도록 transaction 하는 로직 */
    fun transactionToHome()
}
