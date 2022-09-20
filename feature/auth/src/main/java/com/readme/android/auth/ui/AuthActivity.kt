package com.readme.android.auth.ui

import android.os.Bundle
import android.view.View
import android.view.ViewTreeObserver
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.readme.android.auth.R
import com.readme.android.auth.databinding.ActivityAuthBinding
import com.readme.android.auth.ui.AutoLoginConstant.AUTO_LOGIN_FAILURE
import com.readme.android.auth.ui.AutoLoginConstant.AUTO_LOGIN_SUCCESS
import com.readme.android.core_ui.base.BindingActivity
import com.readme.android.core_ui.ext.replace
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class AuthActivity : BindingActivity<ActivityAuthBinding>(R.layout.activity_auth) {
    private val loginViewModel by viewModels<LoginViewModel>()
    lateinit var autoLoginState: AutoLoginConstant

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        checkAutoLoginState()
        autoLogin()
        observeData()
    }

    private fun autoLogin() {
        val content: View = findViewById(android.R.id.content)
        content.viewTreeObserver.addOnPreDrawListener(
            object : ViewTreeObserver.OnPreDrawListener {
                override fun onPreDraw(): Boolean {
                    return if (::autoLoginState.isInitialized) {
                        if (autoLoginState == AUTO_LOGIN_SUCCESS) {
                            mainNavigator.openMain(this@AuthActivity)
                            finish()
                        } else {
                            if (loginViewModel.getIsFirstVisit())
                                replace<OnBoardFragment>(R.id.container_auth)
                            else
                                replace<LoginFragment>(R.id.container_auth)
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

    private fun observeData() {
        loginViewModel.isOnboardEnd.flowWithLifecycle(lifecycle)
            .onEach {
                if (it)
                    replace<LoginFragment>(R.id.container_auth)
            }
            .launchIn(lifecycleScope)
    }

    private fun checkAutoLoginState() {
        if (loginViewModel.getAccessToken() != "") {
            autoLoginState = AUTO_LOGIN_SUCCESS
        } else {
            autoLoginState = AUTO_LOGIN_FAILURE
        }
    }
}
