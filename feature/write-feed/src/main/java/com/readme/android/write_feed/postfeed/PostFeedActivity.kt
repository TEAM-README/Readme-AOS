package com.readme.android.write_feed.postfeed

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.readme.android.core_ui.base.BindingActivity
import com.readme.android.domain.entity.BookInfo
import com.readme.android.write_feed.R
import com.readme.android.write_feed.databinding.ActivityPostFeedBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PostFeedActivity : BindingActivity<ActivityPostFeedBinding>(R.layout.activity_post_feed) {

    private val postFeedViewModel by viewModels<PostFeedViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initExtraData()
    }

    private fun initExtraData() {
        val book: BookInfo = intent.getSerializableExtra("bookInfo") as BookInfo
        Log.d("이창환", "$book")
    }
}
