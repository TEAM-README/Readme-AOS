package com.readme.android.core_ui.ext

import android.view.View
import timber.log.Timber

inline fun View.setOnSingleClickListener(
    delay: Long = 500L,
    crossinline block: (View) -> Unit
) {
    var previousClickedTime = 0L
    setOnClickListener { view ->
        val clickedTime = System.currentTimeMillis()
        if (clickedTime - previousClickedTime >= delay) {
            block(view)
            Timber.d((clickedTime - previousClickedTime).toString())
            previousClickedTime = clickedTime
        }
    }
}