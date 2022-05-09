package com.readme.android.core.util


import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.readme.android.shared.R
import com.readme.android.core.constant.*

object BindingAdapters {

    @JvmStatic
    @BindingAdapter("nickNameStateNumber")
    fun TextView.setNickNameState(nickNameStateNumber: Int?) {
        val context = this.context

        if (nickNameStateNumber != null) {
            when (nickNameStateNumber) {
                OVER_TEXT_LIMIT -> {
                    setTextColor(context.getColor(R.color.alert_red))
                    setText(com.readme.android.shared.R.string.set_nickname_over_text_limit)
                }
                NO_SPECIAL_CHARACTER -> {
                    setTextColor(context.getColor(R.color.alert_red))
                    setText(com.readme.android.shared.R.string.set_nickname_no_special_character)
                }
                DUPLICATE_NICKNAME -> {
                    setTextColor(context.getColor(R.color.alert_red))
                    setText(com.readme.android.shared.R.string.set_nickname_duplicate_nickname)
                }
                ALLOWED_NICKNAME -> {
                    setTextColor(context.getColor(R.color.main_blue))
                    setText(com.readme.android.shared.R.string.set_nickname_allowed_nickname)
                }
                else -> {
                    text = ""
                }
            }
        }
    }
}