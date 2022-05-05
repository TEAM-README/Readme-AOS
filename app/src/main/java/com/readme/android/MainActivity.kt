package com.readme.android

import android.os.Bundle
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
        syncBottomNavWithVp()
    }

    private fun initAdapter() {
        binding.vpMain.adapter = MainViewPagerAdapter(this).also { mainViewPagerAdapter = it }
        mainViewPagerAdapter.fragments.addAll(listOf(HomeFragment(), MyPageFragment()))
    }

    private fun syncBottomNavWithVp() {
        binding.vpMain.isUserInputEnabled = false

        binding.bottomNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.menu_home -> binding.vpMain.setCurrentItem(0, false)
                R.id.menu_my_page -> binding.vpMain.setCurrentItem(1, false)
            }
            return@setOnItemSelectedListener true
        }
    }
}
