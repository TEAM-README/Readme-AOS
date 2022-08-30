package com.readme.android.write_feed.fragments

import android.content.Intent
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.View
import androidx.fragment.app.activityViewModels
import com.readme.android.core_ui.base.BindingFragment
import com.readme.android.core_ui.ext.setOnSingleClickListener
import com.readme.android.domain.entity.BookInfo
import com.readme.android.write_feed.R
import com.readme.android.write_feed.databinding.FragmentFeelingBinding
import com.readme.android.write_feed.postfeed.PostFeedActivity
import com.readme.android.write_feed.writefeed.FeedWriteViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FeelingFragment : BindingFragment<FragmentFeelingBinding>(R.layout.fragment_feeling) {

    private val feedWriteViewModel by activityViewModels<FeedWriteViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.feedWriteViewModel = feedWriteViewModel
        initNextButtonClickListener()
        initScrollTextView()
    }

    private fun initNextButtonClickListener() {
        binding.btnNext.setOnSingleClickListener {
            val intent = Intent(requireActivity(), PostFeedActivity::class.java).apply {
                putExtra(CATEGORY, feedWriteViewModel.category.value)
                putExtra(IMPRESSIVE_SENTENCE, feedWriteViewModel.impressiveSentence.value)
                putExtra(FEELING, feedWriteViewModel.feeling.value)
                putExtra(
                    BOOK_INFO,
                    BookInfo(
                        feedWriteViewModel.title.value ?: "",
                        feedWriteViewModel.author.value ?: "",
                        feedWriteViewModel.image.value ?: "",
                        feedWriteViewModel.isbn,
                        feedWriteViewModel.subIsbn
                    )
                )
            }
            startActivity(intent)
        }
    }

    private fun initScrollTextView() {
        binding.tvFeelingContent.movementMethod = ScrollingMovementMethod()
    }

    companion object {
        const val CATEGORY = "category"
        const val IMPRESSIVE_SENTENCE = "impressiveSentence"
        const val FEELING = "feeling"
        const val BOOK_INFO = "bookInfo"
    }
}
