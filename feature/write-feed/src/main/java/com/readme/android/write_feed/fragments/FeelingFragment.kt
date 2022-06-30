package com.readme.android.write_feed.fragments

import android.os.Bundle
import android.view.View
import com.readme.android.core_ui.base.BindingFragment
import com.readme.android.write_feed.R
import com.readme.android.write_feed.databinding.FragmentFeelingBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FeelingFragment : BindingFragment<FragmentFeelingBinding>(R.layout.fragment_feeling) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}
