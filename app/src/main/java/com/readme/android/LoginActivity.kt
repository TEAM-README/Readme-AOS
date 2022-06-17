package com.readme.android

import android.os.Bundle
import com.readme.android.core_ui.base.BindingActivity
import com.readme.android.databinding.ActivityLoginBinding

class LoginActivity : BindingActivity<ActivityLoginBinding>(R.layout.activity_login) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initClickEvent()
    }

    private fun initClickEvent() {
        with(binding) {
            layoutKakao.setOnClickListener {
                // TODO : 소셜로그인 카카오
            }

            layoutNaver.setOnClickListener {
                // TODO : 소셜로그인 네이버
            }
        }
    }
}