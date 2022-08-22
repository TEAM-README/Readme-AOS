package com.readme.android.write_feed

import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.replace
import com.readme.android.core_ui.base.BindingActivity
import com.readme.android.core_ui.constant.FeedWriteFragmentList.*
import com.readme.android.core_ui.util.KeyboardVisibilityUtils
import com.readme.android.core_ui.util.ResolutionMetrics
import com.readme.android.write_feed.databinding.ActivityFeedWriteBinding
import com.readme.android.write_feed.fragments.ChooseCategoryFragment
import com.readme.android.write_feed.fragments.FeelingFragment
import com.readme.android.write_feed.fragments.ImpressiveSentenceFragment
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
        updateKeyBoardState()
        initStartFragment()
        initButtonNextClickListener()
        initButtonBackClickListener()
        initExtraData()
    }

    override fun onBackPressed() {
        backButtonProcess()
    }

    private fun setFragmentContainerMargin() {
        binding.fragmentContainerMargin = resolutionMetrics.toPixel(112)
    }

    private fun initKeyBoardState() {
        binding.keyboardState = true
    }

    private fun updateKeyBoardState() {
        KeyboardVisibilityUtils(
            this.window,
            onShowKeyboard = {
                binding.keyboardState = false
            },
            onHideKeyboard = {
                binding.keyboardState = true
            }
        )
    }

    private fun initExtraData() {
        feedWriteViewModel.initBookInfo(
            title = intent.getStringExtra("title") ?: "",
            author = intent.getStringExtra("author") ?: "",
            image = intent.getStringExtra("image") ?: "",
            isbn = intent.getStringExtra("isbn") ?: "",
            subIsbn = intent.getStringExtra("subIsbn") ?: ""
        )
    }

    private fun initStartFragment() {
        supportFragmentManager.beginTransaction()
            .replace<ChooseCategoryFragment>(R.id.container_feed_write).commit()
    }

    private fun initButtonNextClickListener() {
        binding.btnNext.setOnClickListener {
            when (requireNotNull(feedWriteViewModel.currentFragment.value)) {
                CHOOSE_CATEGORY -> {
                    feedWriteViewModel.updateCurrentFragment(IMPRESSIVE_SENTENCE)
                    supportFragmentManager.beginTransaction()
                        .replace<ImpressiveSentenceFragment>(R.id.container_feed_write).commit()
                }
                IMPRESSIVE_SENTENCE -> {
                    feedWriteViewModel.updateCurrentFragment(FEELING)
                    supportFragmentManager.beginTransaction()
                        .replace<FeelingFragment>(R.id.container_feed_write).commit()
                }
                FEELING -> {
                    // 다음 액티비티로 넘어가야하는 로직
                }
            }
        }
    }

    private fun initButtonBackClickListener() {
        binding.btnBack.setOnClickListener {
            backButtonProcess()
        }
    }

    private fun backButtonProcess() {
        when (requireNotNull(feedWriteViewModel.currentFragment.value)) {
            CHOOSE_CATEGORY -> {
                finish()
            }
            IMPRESSIVE_SENTENCE -> {
                feedWriteViewModel.updateCurrentFragment(CHOOSE_CATEGORY)
                supportFragmentManager.beginTransaction()
                    .replace<ChooseCategoryFragment>(R.id.container_feed_write).commit()
            }
            FEELING -> {
                feedWriteViewModel.updateCurrentFragment(IMPRESSIVE_SENTENCE)
                supportFragmentManager.beginTransaction()
                    .replace<ImpressiveSentenceFragment>(R.id.container_feed_write).commit()
            }
        }
    }
}
