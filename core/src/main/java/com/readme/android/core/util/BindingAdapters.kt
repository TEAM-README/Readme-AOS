package com.readme.android.core.util

import android.net.Uri
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.readme.android.core.R
import com.readme.android.core.constant.*

object BindingAdapters {

    @JvmStatic
    @BindingAdapter("nickNameStateNumber")
    fun setNickNameState(textView: TextView, nickNameStateNumber: Int?) {
        val context = textView.context

        if(nickNameStateNumber != null){
            when (nickNameStateNumber) {
                OVER_TEXT_LIMIT -> {
                    textView.setTextColor(ContextCompat.getColor(context, com.readme.android.shared.R.color.alert_red))
                    textView.setText(com.readme.android.shared.R.string.set_nickname_over_text_limit)
                }
                NO_SPECIAL_CHARACTER -> {
                    textView.setTextColor(ContextCompat.getColor(context, com.readme.android.shared.R.color.alert_red))
                    textView.setText(com.readme.android.shared.R.string.set_nickname_no_special_character)
                }
                DUPLICATE_NICKNAME -> {
                    textView.setTextColor(ContextCompat.getColor(context, com.readme.android.shared.R.color.alert_red))
                    textView.setText(com.readme.android.shared.R.string.set_nickname_duplicate_nickname)
                }
                ALLOWED_NICKNAME -> {
                    textView.setTextColor(ContextCompat.getColor(context, com.readme.android.shared.R.color.main_blue))
                    textView.setText(com.readme.android.shared.R.string.set_nickname_allowed_nickname)
                }
                else -> {
                    textView.text = ""
                }
            }
        }
    }
}