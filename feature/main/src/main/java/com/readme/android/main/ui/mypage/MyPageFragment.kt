package com.readme.android.main.ui.mypage

import android.os.Bundle
import android.view.View
import com.readme.android.core.base.BindingFragment
import com.readme.android.main.R
import com.readme.android.main.databinding.FragmentMyPageBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MyPageFragment : BindingFragment<FragmentMyPageBinding>(R.layout.fragment_my_page) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    private fun initAdapter() {
        // TODO 콘캣 어댑터가 들어갈거에용
    }
}
