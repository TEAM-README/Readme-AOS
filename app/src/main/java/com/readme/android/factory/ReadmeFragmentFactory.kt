package com.readme.android.factory

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.readme.android.core.util.Injector
import com.readme.android.core.util.ResolutionMetrics
import com.readme.android.main.ui.home.HomeFragment
import com.readme.android.main.ui.mypage.MyPageFragment
import dagger.hilt.android.EntryPointAccessors

class ReadmeFragmentFactory(activity: AppCompatActivity) : FragmentFactory() {
    private val resolutionMetrics: ResolutionMetrics by lazy {
        EntryPointAccessors.fromActivity(
            activity,
            Injector.ResolutionMetricsInjector::class.java
        ).resolutionMetrics()
    }

    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        return when (className) {
            MyPageFragment::class.java.name -> MyPageFragment(resolutionMetrics)
            HomeFragment::class.java.name -> HomeFragment(resolutionMetrics)
            else -> super.instantiate(classLoader, className)
        }
    }
}
