package com.readme.android.main.ui.home

import android.os.Bundle
import android.view.View
import com.readme.android.core_ui.base.BindingFragment
import com.readme.android.main.R
import com.readme.android.main.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BindingFragment<FragmentHomeBinding>(R.layout.fragment_home) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}
