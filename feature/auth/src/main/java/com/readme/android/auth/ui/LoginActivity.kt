package com.readme.android.auth.ui

import android.os.Bundle
import android.view.View
import android.view.ViewTreeObserver
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.navercorp.nid.NaverIdLoginSDK
import com.readme.android.auth.BuildConfig.X_NAVER_CLIENT_ID
import com.readme.android.auth.BuildConfig.X_NAVER_CLIENT_SECRET
import com.readme.android.auth.R
import com.readme.android.auth.databinding.ActivityLoginBinding
import com.readme.android.core_ui.base.BindingActivity
import com.readme.android.core_ui.util.EventObserver
import com.readme.android.navigator.MainNavigator
import com.readme.android.auth.ui.AutoLoginConstant.AUTO_LOGIN_FAILURE
import com.readme.android.auth.ui.AutoLoginConstant.AUTO_LOGIN_SUCCESS
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class LoginActivity : BindingActivity<ActivityLoginBinding>(R.layout.activity_login) {
    @Inject
    lateinit var mainNavigator: MainNavigator
    private val loginViewModel by viewModels<LoginViewModel>()
    lateinit var autoLoginState: AutoLoginConstant

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        checkAutoLoginState()
        initClickEvent()
        initLoginObserver()
        initLoginFailureMessageObserver()
        initMoveToHomeObserver()
        initMoveToSetNickNameObserver()
        activeNaverbtnClickable()
        autoLogin()
    }

    private fun initClickEvent() {
        with(binding) {
            layoutKakao.setOnClickListener {
                // TODO : 소셜로그인 카카오
                loginViewModel.updatePlatform(KAKAO)
            }

            layoutNaver.setOnClickListener {
                it.isClickable = false
                loginViewModel.updatePlatform(NAVER)
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

    private fun initLoginObserver() {
        loginViewModel.socialToken.observe(this) {
            loginViewModel.postLogin()
        }
    }

    private fun initLoginFailureMessageObserver() {
        loginViewModel.loginFailureMessage.observe(this) {
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

    private fun activeNaverbtnClickable() {
        binding.layoutNaver.isClickable = true
    }

    private fun autoLogin() {
        val content: View = findViewById(android.R.id.content)
        content.viewTreeObserver.addOnPreDrawListener(
            object : ViewTreeObserver.OnPreDrawListener {
                override fun onPreDraw(): Boolean {
                    return if (::autoLoginState.isInitialized) {
                        if(autoLoginState == AUTO_LOGIN_SUCCESS){
                            moveMainActivity()
                        }
                        content.viewTreeObserver.removeOnPreDrawListener(this)
                        true
                    } else {
                        false
                    }
                }
            }
        )
    }

    private fun checkAutoLoginState() {
        if (loginViewModel.getAccessToken() != "") {
            autoLoginState = AUTO_LOGIN_SUCCESS
        } else {
            autoLoginState = AUTO_LOGIN_FAILURE
        }
    }

    companion object {
        private const val NAVER = "NAVER"
        private const val KAKAO = "KAKAO"
    }
}
