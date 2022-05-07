package com.readme.android.core.ext

import android.content.Context
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat

fun Context.getColor(@ColorRes res: Int) = ContextCompat.getColor(this, res)