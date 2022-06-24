package com.readme.android.auth.ui

import android.os.Bundle
import com.readme.android.auth.R
import com.readme.android.auth.databinding.ActivityLoginBinding
import com.readme.android.core_ui.base.BindingActivity
import com.readme.android.navigator.MainNavigator
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class LoginActivity : BindingActivity<ActivityLoginBinding>(R.layout.activity_login) {
    @Inject
    lateinit var mainNavigator: MainNavigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initClickEvent()
    }

    private fun initClickEvent() {
        with(binding) {
            layoutKakao.setOnClickListener {
                // TODO : 소셜로그인 카카오
                moveMainActivity()
            }

            layoutNaver.setOnClickListener {
                // TODO : 소셜로그인 네이버
                moveMainActivity()
            }
        }
    }

    private fun moveMainActivity() {
        mainNavigator.openMain(this)
        finish()
    }
}
