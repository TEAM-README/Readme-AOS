package com.readme.android.core_ui.ext

import android.app.Activity
import androidx.annotation.ColorInt
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.replace

fun Activity.setStatusBarColor(@ColorInt color: Int) {
    this.window.run { statusBarColor = color }
}

fun Fragment.setStatusBarColor(@ColorInt color: Int) {
    requireActivity().window.run { statusBarColor = color }
}

inline fun <reified T : Fragment> AppCompatActivity.replace(@IdRes frameId: Int) {
    supportFragmentManager.commit {
        replace<T>(frameId)
        setReorderingAllowed(true)
    }
}
