package com.readme.android.write_feed.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.replace
import com.readme.android.core_ui.base.BindingFragment
import com.readme.android.core_ui.constant.FeedWriteFragmentList.FEELING
import com.readme.android.write_feed.FeedWriteViewModel
import com.readme.android.write_feed.R
import com.readme.android.write_feed.databinding.FragmentImpressiveSentenceBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ImpressiveSentenceFragment :
    BindingFragment<FragmentImpressiveSentenceBinding>(R.layout.fragment_impressive_sentence) {

    private val feedWriteViewModel by activityViewModels<FeedWriteViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initNextButtonClickListener()
    }

    private fun initNextButtonClickListener() {
        binding.btnNext.setOnClickListener {
            feedWriteViewModel.updateCurrentFragment(FEELING)
            requireActivity().supportFragmentManager.beginTransaction()
                .replace<FeelingFragment>(R.id.container_feed_write).commit()
        }
    }
}
