package com.readme.android.set_nick_name

import android.os.Bundle
import androidx.activity.viewModels
import com.readme.android.core.base.BindingActivity
import com.readme.android.set_nick_name.databinding.ActivitySetNickNameBinding

class SetNickNameActivity : BindingActivity<ActivitySetNickNameBinding>(R.layout.activity_set_nick_name) {

    private val setNickNameViewModel by viewModels<SetNickNameViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.setNickNameViewModel = setNickNameViewModel
        binding.lifecycleOwner = this

    }


    
}