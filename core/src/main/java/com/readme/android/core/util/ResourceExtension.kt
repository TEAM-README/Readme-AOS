package com.readme.android.core.util

import android.view.View
import androidx.annotation.ColorRes
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment

fun View.getColor(@ColorRes res: Int) = context.getColor(res)
fun Fragment.getColor(@ColorRes res: Int) = requireContext().getColor(res)
fun View.getString(@StringRes res: Int) = context.getString(res)
fun Fragment.getString(@StringRes res: Int) = requireContext().getString(res)
