package com.readme.android.write_feed.postfeed

import android.os.Bundle
import com.readme.android.core_ui.base.BindingActivity
import com.readme.android.navigator.MainNavigator
import com.readme.android.write_feed.R
import com.readme.android.write_feed.databinding.ActivityRegisterDoneBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

class RegisterDoneActivity :
    BindingActivity<ActivityRegisterDoneBinding>(R.layout.activity_register_done) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initNextButtonClickListener()
    }

    private fun initNextButtonClickListener() {
        binding.btnNext.setOnClickListener {
            finish()
        }
    }
}
