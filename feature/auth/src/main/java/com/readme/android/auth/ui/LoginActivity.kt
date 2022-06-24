package com.readme.android.auth.ui

import android.os.Bundle
import androidx.activity.viewModels
import com.navercorp.nid.NaverIdLoginSDK
import com.navercorp.nid.oauth.OAuthLoginCallback
import com.readme.android.auth.BuildConfig.X_NAVER_CLIENT_ID
import com.readme.android.auth.BuildConfig.X_NAVER_CLIENT_SECRET
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
    private val loginViewModel by viewModels<LoginViewModel>()

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
                loginViewModel.setOAuthLoginCallback()
                NaverIdLoginSDK.initialize(this@LoginActivity,X_NAVER_CLIENT_ID,X_NAVER_CLIENT_SECRET,"ReadMe")
                NaverIdLoginSDK.authenticate(this@LoginActivity,loginViewModel.oAuthLoginCallback)
//                moveMainActivity()
            }
        }
    }

    private fun moveMainActivity() {
        mainNavigator.openMain(this)
        finish()
    }
}
