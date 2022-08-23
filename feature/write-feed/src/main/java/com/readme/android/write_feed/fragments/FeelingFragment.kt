package com.readme.android.write_feed.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import com.readme.android.core_ui.base.BindingFragment
import com.readme.android.write_feed.FeedWriteViewModel
import com.readme.android.write_feed.R
import com.readme.android.write_feed.databinding.FragmentFeelingBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FeelingFragment : BindingFragment<FragmentFeelingBinding>(R.layout.fragment_feeling) {

    private val feedWriteViewModel by activityViewModels<FeedWriteViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initNextButtonClickListener()
    }

    private fun initNextButtonClickListener() {
        binding.btnNext.setOnClickListener {
        }
    }
}
