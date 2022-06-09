package com.readme.android.core.util


import android.net.Uri
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.view.marginTop
import androidx.core.view.setMargins
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.readme.android.shared.R
import com.readme.android.core.constant.*
import com.readme.android.core.util.BindingAdapters.setNickNameState
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.EntryPointAccessors
import javax.inject.Inject

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

    @JvmStatic
    @BindingAdapter("setNickNameEditTextBackGround")
    fun EditText.setNickNameEditTextBackGround(nickNameStateNumber: Int?) {
        if (nickNameStateNumber != null) {
            this.setBackgroundResource(
                when (nickNameStateNumber) {
                    OVER_TEXT_LIMIT -> R.drawable.shape_rect_alert_red_1_border_10
                    NO_SPECIAL_CHARACTER -> R.drawable.shape_rect_alert_red_1_border_10
                    DUPLICATE_NICKNAME -> R.drawable.shape_rect_alert_red_1_border_10
                    ALLOWED_NICKNAME -> R.drawable.shape_rect_gray_1_boder_10
                    HAS_NO_STATE -> R.drawable.shape_rect_gray_1_boder_10
                    else -> R.drawable.shape_rect_gray_1_boder_10
                }
            )
        }
    }

    @JvmStatic
    @BindingAdapter("setDuplicateButtonState", "editTextLength")
    fun TextView.setDuplicateButtonState(nickNameStateNumber: Int?, editTextLength: Int?) {
        val context = this.context

        if (nickNameStateNumber != null) {
            if (editTextLength ?: 0 > 0) {
                when (nickNameStateNumber) {
                    OVER_TEXT_LIMIT -> {
                        setTextColor(context.getColor(R.color.gray_1))
                        isClickable = false
                    }
                    NO_SPECIAL_CHARACTER -> {
                        setTextColor(context.getColor(R.color.gray_1))
                        isClickable = false
                    }
                    DUPLICATE_NICKNAME -> {
                        setTextColor(context.getColor(R.color.gray_1))
                        isClickable = false
                    }
                    ALLOWED_NICKNAME -> {
                        setTextColor(context.getColor(R.color.gray_1))
                        isClickable = false
                    }
                    HAS_NO_STATE -> {
                        setTextColor(context.getColor(R.color.main_blue))
                        isClickable = true
                    }
                    else -> {
                        setTextColor(context.getColor(R.color.gray_1))
                        isClickable = false
                    }
                }
            }
        }
    }


    @JvmStatic
    @BindingAdapter("setRecyclerViewMarginStandardView","recentReadVisibleMargin","recentReadGoneMargin")
    fun RecyclerView.setBookSearchRecentReadStateMargin(textView: TextView, recentReadVisibleMargin: Int, recentReadGoneMargin: Int) {
        if (textView.visibility == View.VISIBLE) {
            val layoutParams = this.layoutParams as ViewGroup.MarginLayoutParams
            layoutParams.topMargin = recentReadVisibleMargin
            this.layoutParams = layoutParams
        } else {
            val layoutParams = this.layoutParams as ViewGroup.MarginLayoutParams
            layoutParams.topMargin = recentReadGoneMargin
            this.layoutParams = layoutParams
        }
    }

    @JvmStatic
    @BindingAdapter("setBookImage")
    fun setBookImage(imageview: ImageView, url: String?) {
        imageview.load(url) {
            error(R.color.gray_2)
            placeholder(R.color.gray_2)
        }

    }
}

