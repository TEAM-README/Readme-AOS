package com.readme.android.main.ui.feed

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import com.readme.android.core_ui.base.BindingActivity
import com.readme.android.main.R
import com.readme.android.main.databinding.ActivityFeedDetailBinding

class FeedDetailActivity : BindingActivity<ActivityFeedDetailBinding>(R.layout.activity_feed_detail) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getFeedIdFromExtra()
    }

    private fun getFeedIdFromExtra(){
        val feedId = intent.getIntExtra(FEED_ID, -1)
        Log.d(TAG, "getFeedIdFromExtra: $feedId")
    }

    companion object{
        const val FEED_ID = "FEED_ID"
    }
}
