package com.readme.android.core_ui.ext

import android.content.Context
import android.app.Activity
import android.content.Context.INPUT_METHOD_SERVICE
import android.content.Intent
import android.content.Intent.ACTION_SEND
import android.content.Intent.EXTRA_EMAIL
import android.net.Uri
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.core.os.bundleOf
import com.readme.android.shared.R

fun Context.showKeyboard(view: View) {
    val inputMethodManager = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.showSoftInput(view, 0)
}

fun Context.closeKeyboard(view: View) {
    val inputMethodManager = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
}

fun Context.shortToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

inline fun <reified T : Activity> Context.buildIntent(
    vararg argument: Pair<String, Any?>
) = Intent(this, T::class.java).apply {
    putExtras(bundleOf(*argument))
}

inline fun <reified T : Activity> Context.navigateActivity(
    vararg argument: Pair<String, Any?>
) {
    startActivity(buildIntent<T>(*argument))
}

fun Context.startUri(
    url: String,
    vararg argument: Pair<String, Any?>
) {
    startActivity(
        Intent(Intent.ACTION_VIEW, Uri.parse(url)).apply {
            putExtras(bundleOf(*argument))
        }
    )
}

fun Context.startMail(
    vararg argument: Pair<String, Any?>
) {
    startActivity(
        Intent(ACTION_SEND).apply {
            type = "plain/text"
            putExtra(EXTRA_EMAIL, resources.getStringArray(R.array.email_array))
            putExtras(bundleOf(*argument))
        }
    )
}
