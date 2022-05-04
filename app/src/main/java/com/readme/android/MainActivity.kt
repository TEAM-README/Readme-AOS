package com.readme.android

import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.readme.android.adapter.MainViewPagerAdapter
import com.readme.android.core.base.BindingActivity
import com.readme.android.databinding.ActivityMainBinding
import com.readme.android.main.ui.home.HomeFragment
import com.readme.android.main.ui.mypage.MyPageFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BindingActivity<ActivityMainBinding>(R.layout.activity_main) {
    private lateinit var mainViewPagerAdapter: MainViewPagerAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initAdapter()
        syncVpWithBottomNav()
    }

    private fun initAdapter() {
        binding.vpMain.adapter = MainViewPagerAdapter(this).also { mainViewPagerAdapter = it }
        mainViewPagerAdapter.fragments.addAll(listOf(HomeFragment(), MyPageFragment()))
    }

    private fun syncVpWithBottomNav() {
        binding.vpMain.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                binding.bottomNav.menu.getItem(if (position == 0) 0 else 2).isChecked = true
            }
        })

        binding.bottomNav.setOnItemSelectedListener {
            binding.vpMain.currentItem = when (it.itemId) {
                R.id.menu_home -> 0
                R.id.menu_my_page -> 1
                else -> return@setOnItemSelectedListener false
            }
            return@setOnItemSelectedListener true
        }
    }
}
