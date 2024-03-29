package com.readme.android.write_feed.postfeed

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import coil.load
import com.readme.android.core_ui.base.BindingActivity
import com.readme.android.core_ui.ext.setOnSingleClickListener
import com.readme.android.core_ui.util.EventObserver
import com.readme.android.domain.entity.BookInfo
import com.readme.android.navigator.MainNavigator
import com.readme.android.shared.R.drawable.img_book_none
import com.readme.android.write_feed.R
import com.readme.android.write_feed.databinding.ActivityPostFeedBinding
import com.readme.android.write_feed.fragments.FeelingFragment.Companion.BOOK_INFO
import com.readme.android.write_feed.fragments.FeelingFragment.Companion.CATEGORY
import com.readme.android.write_feed.fragments.FeelingFragment.Companion.FEELING
import com.readme.android.write_feed.fragments.FeelingFragment.Companion.IMPRESSIVE_SENTENCE
import com.readme.android.write_feed.writefeed.FeedWriteActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class PostFeedActivity : BindingActivity<ActivityPostFeedBinding>(R.layout.activity_post_feed) {
    private val postFeedViewModel by viewModels<PostFeedViewModel>()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        terminationTokenHandling(postFeedViewModel)
        binding.postFeedViewModel = postFeedViewModel
        initExtraData()
        initWriteFeedDate()
        getUserNickName()
        initScrollTextView()
        initBookInfoView()
        initNextButtonClickListener()
        initPostFeedStateObserver()
        initBackButtonClickListener()
    }

    private fun initExtraData() {
        postFeedViewModel.initPostFeedData(
            category = intent.getStringExtra(CATEGORY) ?: "",
            impressiveSentence = intent.getStringExtra(IMPRESSIVE_SENTENCE) ?: "",
            feeling = intent.getStringExtra(FEELING) ?: "",
            bookInfo = intent.getSerializableExtra(BOOK_INFO) as BookInfo
        )
    }

    private fun getUserNickName() {
        postFeedViewModel.getUserNickName()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun initWriteFeedDate() {
        postFeedViewModel.initWriteFeedDate()
    }

    private fun initScrollTextView() {
        binding.tvImpressiveSentence.movementMethod = ScrollingMovementMethod()
        binding.tvFeeling.movementMethod = ScrollingMovementMethod()
    }

    private fun initBookInfoView() {
        binding.layoutBookInformation.book = postFeedViewModel.bookInfo.value
        binding.layoutBookInformation.ivBook.load(postFeedViewModel.bookInfo.value?.image) {
            placeholder(img_book_none)
            error(img_book_none)
        }
        binding.layoutBookInformation.tvCategory.text =
            postFeedViewModel.category.value
    }

    private fun initNextButtonClickListener() {
        binding.btnNext.setOnSingleClickListener {
            postFeedViewModel.postFeed()
        }
    }

    private fun initPostFeedStateObserver() {
        postFeedViewModel.postFeedState.observe(
            this,
            EventObserver {
                mainNavigator.transactionToHome()
                FeedWriteActivity.feedWriteActivity.finish()
                val intent = Intent(this, RegisterDoneActivity::class.java)
                startActivity(intent)
                finish()
            }
        )
    }

    private fun initBackButtonClickListener() {
        binding.btnBack.setOnSingleClickListener {
            finish()
        }
    }
}
