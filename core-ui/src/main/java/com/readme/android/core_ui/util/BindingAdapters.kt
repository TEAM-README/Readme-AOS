package com.readme.android.core_ui.util

import android.os.Build
import android.text.Html
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.databinding.BindingAdapter
import androidx.fragment.app.FragmentContainerView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.readme.android.core_ui.constant.FeedWriteFragmentList
import com.readme.android.core_ui.constant.FeedWriteFragmentList.*
import com.readme.android.core_ui.constant.SetNickNameConstant
import com.readme.android.core_ui.constant.SetNickNameConstant.*
import com.readme.android.shared.R


@BindingAdapter("nickNameStateNumber")
fun TextView.setNickNameState(nickNameStateNumber: SetNickNameConstant?) {
    val context = this.context

    if (nickNameStateNumber != null) {
        when (nickNameStateNumber) {
            OVER_TEXT_LIMIT -> {
                setTextColor(context.getColor(R.color.alert_red))
                setText(R.string.set_nickname_over_text_limit)
            }
            NO_SPECIAL_CHARACTER -> {
                setTextColor(context.getColor(R.color.alert_red))
                setText(R.string.set_nickname_no_special_character)
            }
            DUPLICATE_NICKNAME -> {
                setTextColor(context.getColor(R.color.alert_red))
                setText(R.string.set_nickname_duplicate_nickname)
            }
            ALLOWED_NICKNAME -> {
                setTextColor(context.getColor(R.color.main_blue))
                setText(R.string.set_nickname_allowed_nickname)
            }
            HAS_NO_STATE -> {
                text = ""
            }
        }
    }
}


@BindingAdapter("setNickNameEditTextBackGround")
fun EditText.setNickNameEditTextBackGround(nickNameStateNumber: SetNickNameConstant?) {
    if (nickNameStateNumber != null) {
        this.setBackgroundResource(
            when (nickNameStateNumber) {
                OVER_TEXT_LIMIT -> R.drawable.shape_rect_alert_red_1_border_10
                NO_SPECIAL_CHARACTER -> R.drawable.shape_rect_alert_red_1_border_10
                DUPLICATE_NICKNAME -> R.drawable.shape_rect_alert_red_1_border_10
                ALLOWED_NICKNAME -> R.drawable.shape_rect_main_bule_border_10
                HAS_NO_STATE -> R.drawable.shape_rect_gray_1_boder_10
            }
        )
    }
}


@BindingAdapter("setDuplicateButtonState", "editTextLength")
fun TextView.setDuplicateButtonState(
    nickNameStateNumber: SetNickNameConstant?,
    editTextLength: Int?
) {
    val context = this.context

    if (nickNameStateNumber != null) {
        if (editTextLength ?: 0 > 0) {
            when (nickNameStateNumber) {
                OVER_TEXT_LIMIT -> {
                    setTextColor(context.getColor(R.color.main_blue))
                    isClickable = true
                }
                NO_SPECIAL_CHARACTER -> {
                    setTextColor(context.getColor(R.color.main_blue))
                    isClickable = true
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
            }
        }
    }
}


@BindingAdapter("currentReadState", "recentReadVisibleMargin", "recentReadGoneMargin")
fun RecyclerView.setBookSearchRecentReadStateMargin(
    currentReadState: Boolean,
    recentReadVisibleMargin: Int,
    recentReadGoneMargin: Int
) {
    if (currentReadState) {
        val layoutParams = this.layoutParams as ViewGroup.MarginLayoutParams
        layoutParams.topMargin = recentReadVisibleMargin
        this.layoutParams = layoutParams
    } else {
        val layoutParams = this.layoutParams as ViewGroup.MarginLayoutParams
        layoutParams.topMargin = recentReadGoneMargin
        this.layoutParams = layoutParams
    }
}


@BindingAdapter("setBookImage")
fun ImageView.setBookImage(bookImageUrl: String?) {
    this.load(bookImageUrl) {
        error(R.color.gray_0)
        placeholder(R.color.gray_0)
    }
}

@RequiresApi(Build.VERSION_CODES.N)
@BindingAdapter("setBookTitle")
fun TextView.setBookTitle(bookTitle: String?) {
    this.text = Html.fromHtml(bookTitle, Html.TO_HTML_PARAGRAPH_LINES_INDIVIDUAL)
}

@BindingAdapter("writeFeedContent")
fun TextView.writeFeedContent(FragmentState: FeedWriteFragmentList) {
    when (FragmentState) {
        CHOOSE_CATEGORY -> this.setText(R.string.choose_category_content)
        IMPRESSIVE_SENTENCE -> this.setText(R.string.impressive_sentence_content)
        FEELING -> this.setText(R.string.feeling_content)
    }
}

@BindingAdapter("keyBoardState", "contentMargin")
fun FragmentContainerView.setFragmentMargin(
    keyBoardState: Boolean,
    contentMargin: Int
) {
    if (keyBoardState) {
        val layoutParams = this.layoutParams as ViewGroup.MarginLayoutParams
        layoutParams.topMargin = contentMargin
        this.layoutParams = layoutParams
    } else {
        val layoutParams = this.layoutParams as ViewGroup.MarginLayoutParams
        layoutParams.topMargin = 0
        this.layoutParams = layoutParams
    }
}
