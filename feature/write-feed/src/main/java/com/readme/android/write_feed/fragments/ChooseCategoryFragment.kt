package com.readme.android.write_feed.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.replace
import com.readme.android.core_ui.base.BindingFragment
import com.readme.android.core_ui.constant.FeedWriteFragmentList.IMPRESSIVE_SENTENCE
import com.readme.android.write_feed.FeedWriteViewModel
import com.readme.android.write_feed.R
import com.readme.android.write_feed.databinding.FragmentChooseCategoryBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChooseCategoryFragment :
    BindingFragment<FragmentChooseCategoryBinding>(R.layout.fragment_choose_category) {

    private val feedWriteViewModel by activityViewModels<FeedWriteViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initNextButtonClickListener()
    }

    private fun initNextButtonClickListener() {
        binding.btnNext.setOnClickListener {
            feedWriteViewModel.updateCurrentFragment(IMPRESSIVE_SENTENCE)
            requireActivity().supportFragmentManager.beginTransaction()
                .replace<ImpressiveSentenceFragment>(R.id.container_feed_write).commit()
        }
    }
}
