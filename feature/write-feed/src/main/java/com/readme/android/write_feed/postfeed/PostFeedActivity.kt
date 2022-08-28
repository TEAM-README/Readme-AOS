package com.readme.android.write_feed.postfeed

import android.os.Build
import android.os.Bundle
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import com.readme.android.core_ui.base.BindingActivity
import com.readme.android.domain.entity.BookInfo
import com.readme.android.write_feed.R
import com.readme.android.write_feed.databinding.ActivityPostFeedBinding
import com.readme.android.write_feed.fragments.FeelingFragment.Companion.BOOK_INFO
import com.readme.android.write_feed.fragments.FeelingFragment.Companion.FEELING
import com.readme.android.write_feed.fragments.FeelingFragment.Companion.IMPRESSIVE_SENTENCE
import com.readme.android.write_feed.fragments.FeelingFragment.Companion.REPRESENT_CATEGORY_STRING
import com.readme.android.write_feed.fragments.FeelingFragment.Companion.WHOLE_CATEGORY_STRING
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PostFeedActivity : BindingActivity<ActivityPostFeedBinding>(R.layout.activity_post_feed) {

    private val postFeedViewModel by viewModels<PostFeedViewModel>()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initExtraData()
        getWriteFeedDate()
        getUserNickName()
    }

    private fun initExtraData() {
        postFeedViewModel.initPostFeedData(
            wholeCategoryString = intent.getStringExtra(WHOLE_CATEGORY_STRING) ?: "",
            representCategoryString = intent.getStringExtra(REPRESENT_CATEGORY_STRING) ?: "",
            impressiveSentence = intent.getStringExtra(IMPRESSIVE_SENTENCE) ?: "",
            feeling = intent.getStringExtra(FEELING) ?: "",
            bookInfo = intent.getSerializableExtra(BOOK_INFO) as BookInfo
        )
    }

    private fun getUserNickName() {
        postFeedViewModel.getUserNickName()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun getWriteFeedDate() {
        postFeedViewModel.getWriteFeedDate()
    }
}
