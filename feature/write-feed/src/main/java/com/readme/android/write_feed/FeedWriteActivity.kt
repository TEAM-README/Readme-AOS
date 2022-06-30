package com.readme.android.write_feed

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.readme.android.core_ui.base.BindingActivity
import com.readme.android.write_feed.databinding.ActivityFeedWriteBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FeedWriteActivity : BindingActivity<ActivityFeedWriteBinding>(R.layout.activity_feed_write) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

}
