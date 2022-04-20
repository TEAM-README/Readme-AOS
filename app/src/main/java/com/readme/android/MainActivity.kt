package com.readme.android

import android.os.Bundle
import com.readme.android.core.base.BindingActivity
import com.readme.android.databinding.ActivityMainBinding

class MainActivity : BindingActivity<ActivityMainBinding>(R.layout.activity_main) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}
