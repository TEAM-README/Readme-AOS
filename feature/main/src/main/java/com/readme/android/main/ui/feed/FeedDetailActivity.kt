package com.readme.android.main.ui.feed

import android.os.Bundle
import androidx.activity.viewModels
import com.readme.android.core_ui.base.BindingActivity
import com.readme.android.main.R
import com.readme.android.main.databinding.ActivityFeedDetailBinding
import com.readme.android.main.viewmodel.DetailFeedViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FeedDetailActivity : BindingActivity<ActivityFeedDetailBinding>(R.layout.activity_feed_detail) {
    private val viewModel : DetailFeedViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setFeedIdOnViewModelFromExtra()
        getDetailFeedInfo()
    }

    private fun setFeedIdOnViewModelFromExtra(){
        val feedId = getFeedIdFromExtra()
        viewModel.setFeedId(feedId)
    }

    private fun getDetailFeedInfo(){
        viewModel.getDetailFeedInfo()
    }

    private fun getFeedIdFromExtra() : Int = intent.getIntExtra(FEED_ID, -1)

    companion object{
        const val FEED_ID = "FEED_ID"
    }
}
