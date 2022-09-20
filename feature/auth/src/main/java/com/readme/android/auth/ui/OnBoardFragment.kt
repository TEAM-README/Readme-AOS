package com.readme.android.auth.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.viewpager2.widget.ViewPager2
import com.readme.android.auth.R
import com.readme.android.auth.databinding.FragmentOnBoardBinding
import com.readme.android.auth.ui.adapter.OnBoardAdapter
import com.readme.android.core_ui.base.BindingFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OnBoardFragment : BindingFragment<FragmentOnBoardBinding>(R.layout.fragment_on_board) {
    private val loginViewModel by activityViewModels<LoginViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            vpOnboard.adapter = OnBoardAdapter()
            indicatorOnboard.attachTo(vpOnboard)

            tvSkip.setOnClickListener {
                loginViewModel.setIsOnboardEnd(true)
            }

            tvNext.setOnClickListener {
                // TODO : 상수로 바꿀 필요 있음..
                if(vpOnboard.currentItem == 2)
                    loginViewModel.setIsOnboardEnd(true)
                else
                    vpOnboard.currentItem += 1
            }

            vpOnboard.registerOnPageChangeCallback(
                object : ViewPager2.OnPageChangeCallback() {
                    override fun onPageSelected(position: Int) {
                        super.onPageSelected(position)
                        binding.onboardNum = position
                    }
                }
            )
        }
    }
}
