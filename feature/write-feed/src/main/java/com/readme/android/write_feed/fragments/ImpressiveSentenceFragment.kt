package com.readme.android.write_feed.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.replace
import coil.load
import com.readme.android.core_ui.base.BindingFragment
import com.readme.android.core_ui.constant.FeedWriteFragmentList.FEELING
import com.readme.android.core_ui.ext.setOnSingleClickListener
import com.readme.android.domain.entity.BookInfo
import com.readme.android.shared.R.drawable.img_book_none
import com.readme.android.write_feed.writefeed.FeedWriteViewModel
import com.readme.android.write_feed.R
import com.readme.android.write_feed.databinding.FragmentImpressiveSentenceBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ImpressiveSentenceFragment :
    BindingFragment<FragmentImpressiveSentenceBinding>(R.layout.fragment_impressive_sentence) {

    private val feedWriteViewModel by activityViewModels<FeedWriteViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.feedWriteViewModel = feedWriteViewModel
        initNextButtonClickListener()
        initBookInfoView()
    }

    private fun initNextButtonClickListener() {
        binding.btnNext.setOnSingleClickListener {
            feedWriteViewModel.updateCurrentFragment(FEELING)
            requireActivity().supportFragmentManager.beginTransaction()
                .replace<FeelingFragment>(R.id.container_feed_write).commit()
        }
    }

    private fun initBookInfoView() {
        binding.layoutBookInformation.book = BookInfo(
            feedWriteViewModel.title.value ?: "",
            feedWriteViewModel.author.value ?: "",
            feedWriteViewModel.image.value ?: "",
            feedWriteViewModel.isbn,
            feedWriteViewModel.subIsbn
        )
        binding.layoutBookInformation.ivBook.load(feedWriteViewModel.image.value ?: "") {
            placeholder(img_book_none)
            error(img_book_none)
        }
        binding.layoutBookInformation.tvCategory.text = feedWriteViewModel.representCategoryString.value
    }
}
