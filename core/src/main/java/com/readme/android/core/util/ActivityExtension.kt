package com.readme.android.core.util

import android.app.Activity
import androidx.annotation.ColorInt
import androidx.fragment.app.Fragment

fun Activity.setStatusBarColor(@ColorInt color: Int) {
    this.window.run { statusBarColor = color }
}

fun Fragment.setStatusBarColor(@ColorInt color: Int) {
    requireActivity().window.run { statusBarColor = color }
}
