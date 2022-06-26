package com.readme.android.auth.ui

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.navercorp.nid.NaverIdLoginSDK
import com.navercorp.nid.oauth.OAuthLoginCallback
import com.readme.android.auth.BuildConfig.X_NAVER_CLIENT_ID
import com.readme.android.auth.BuildConfig.X_NAVER_CLIENT_SECRET
import com.readme.android.auth.R
import com.readme.android.auth.databinding.ActivityLoginBinding
import com.readme.android.core_ui.base.BindingActivity
import com.readme.android.core_ui.util.EventObserver
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
        initNaverLoginObserver()
        initNaverLoginFaulureMessageObserver()
        initMoveToHomeObserver()
        initMoveToSetNickNameObserver()
        activeNaverbtnClickable()
    }

    private fun initClickEvent() {
        with(binding) {
            layoutKakao.setOnClickListener {
                // TODO : 소셜로그인 카카오
                moveMainActivity()
            }

            layoutNaver.setOnClickListener {
                it.isClickable = false
                moveSetNickNameActivity("NAVER","asd")
                loginViewModel.naverSetOAuthLoginCallback()
                NaverIdLoginSDK.initialize(
                    this@LoginActivity,
                    X_NAVER_CLIENT_ID,
                    X_NAVER_CLIENT_SECRET,
                    "ReadMe"
                )
                NaverIdLoginSDK.authenticate(this@LoginActivity, loginViewModel.oAuthLoginCallback)
            }
        }
    }

    private fun initNaverLoginObserver() {
        loginViewModel.socialToken.observe(this) {
            loginViewModel.postNaverLogin()
        }
    }

    private fun initNaverLoginFaulureMessageObserver() {
        loginViewModel.naverLoginFailureMessage.observe(this) {
            Toast.makeText(this, "로그인에 실패 하였습니다", Toast.LENGTH_SHORT).show()
        }
    }

    private fun initMoveToSetNickNameObserver() {
        loginViewModel.moveToSetNickname.observe(this, EventObserver {
            moveSetNickNameActivity(it, loginViewModel.socialToken.value ?: "")
        })
    }

    private fun initMoveToHomeObserver() {
        loginViewModel.moveToHome.observe(this, EventObserver {
            moveMainActivity()
        })
    }

    private fun moveMainActivity() {
        mainNavigator.openMain(this)
        finish()
    }

    private fun moveSetNickNameActivity(platform: String, socialToken: String) {
        mainNavigator.openSetNickName(
            context = this,
            platform = Pair("platform", platform),
            socialToken = Pair("socialToken", socialToken)
        )
        finish()
    }

    private fun activeNaverbtnClickable(){
        binding.layoutNaver.isClickable = true
    }
}
