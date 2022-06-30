package com.readme.android.write_feed

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.readme.android.core_ui.base.BindingActivity
import com.readme.android.core_ui.util.KeyboardVisibilityUtils
import com.readme.android.core_ui.util.ResolutionMetrics
import com.readme.android.write_feed.databinding.ActivityFeedWriteBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class FeedWriteActivity : BindingActivity<ActivityFeedWriteBinding>(R.layout.activity_feed_write) {

    private val feedWriteViewModel by viewModels<FeedWriteViewModel>()

    @Inject
    lateinit var resolutionMetrics: ResolutionMetrics

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.feedWriteViewModel = feedWriteViewModel
        feedWriteViewModel.getUserNickName()
        setFragmentContainerMargin()
        initKeyBoardState()
    }

    private fun setFragmentContainerMargin(){
        binding.fragmentContainerMargin = resolutionMetrics.toPixel(112)
    }

    private fun initKeyBoardState(){
        binding.keyboardState = true
    }

    private fun updateKeyBoardState(){
        KeyboardVisibilityUtils(this.window,
        onShowKeyboard = {
            binding.keyboardState = false
        },
        onHideKeyboard = {
            binding.keyboardState = true
        })
    }
}
