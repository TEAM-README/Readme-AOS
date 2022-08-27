package com.readme.android.write_feed.fragments

import android.content.Intent
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.View
import androidx.fragment.app.activityViewModels
import com.readme.android.core_ui.base.BindingFragment
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
        binding.btnNext.setOnClickListener {
            val intent = Intent(requireActivity(), PostFeedActivity::class.java).apply {
                putExtra("wholeCategoryString", feedWriteViewModel.wholeCategoryString.value)
                putExtra(
                    "representCategoryString",
                    feedWriteViewModel.representCategoryString.value
                )
                putExtra("impressiveSentence", feedWriteViewModel.impressiveSentence.value)
                putExtra("feeling", feedWriteViewModel.feeling.value)
                putExtra(
                    "bookInfo",
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
            requireActivity().finish()
        }
    }

    private fun initScrollTextView() {
        binding.tvFeelingContent.movementMethod = ScrollingMovementMethod()
    }
}
