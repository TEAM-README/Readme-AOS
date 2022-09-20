package com.readme.android.auth.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.readme.android.auth.R
import com.readme.android.auth.databinding.FragmentLoginBinding
import com.readme.android.auth.social_login_manager.KakaoLoginManager
import com.readme.android.auth.social_login_manager.NaverLoginManager
import com.readme.android.core_ui.base.BindingFragment
import com.readme.android.core_ui.ext.setOnSingleClickListener
import com.readme.android.core_ui.util.EventObserver
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class LoginFragment : BindingFragment<FragmentLoginBinding>(R.layout.fragment_login) {
    @Inject
    lateinit var naverLoginManager: NaverLoginManager

    @Inject
    lateinit var kakaoLoginManager: KakaoLoginManager

    private val loginViewModel by activityViewModels<LoginViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initClickEvent()
        initLoginObserver()
        initLoginFailureMessageObserver()
        initMoveToHomeObserver()
        initMoveToSetNickNameObserver()
    }

    private fun initClickEvent() {
        with(binding) {
            layoutKakao.setOnSingleClickListener {
                loginViewModel.updatePlatform(KAKAO)
                kakaoLoginManager.startKakaoLogin {
                    loginViewModel.updateSocialToken(it)
                }
            }

            layoutNaver.setOnSingleClickListener {
                loginViewModel.updatePlatform(NAVER)
                naverLoginManager.startNaverLogin {
                    loginViewModel.updateSocialToken(it)
                }
            }
        }
    }

    private fun initLoginObserver() {
        loginViewModel.socialToken.observe(viewLifecycleOwner) {
            loginViewModel.postLogin()
        }
    }

    private fun initLoginFailureMessageObserver() {
        loginViewModel.loginFailureMessage.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), "로그인에 실패 하였습니다", Toast.LENGTH_SHORT).show()
        }
    }

    private fun initMoveToSetNickNameObserver() {
        loginViewModel.moveToSetNickname.observe(
            viewLifecycleOwner,
            EventObserver {
                moveSetNickNameActivity(it, loginViewModel.socialToken.value ?: "")
            }
        )
    }

    private fun initMoveToHomeObserver() {
        loginViewModel.moveToHome.observe(
            viewLifecycleOwner,
            EventObserver {
                moveMainActivity()
            }
        )
    }

    private fun moveMainActivity() {
         mainNavigator.openMain(requireActivity())
        requireActivity().finish()
    }

    private fun moveSetNickNameActivity(platform: String, socialToken: String) {
        mainNavigator.openSetNickName(
            context = requireContext(),
            platform = Pair("platform", platform),
            socialToken = Pair("socialToken", socialToken)
        )
        requireActivity().finish()
    }

    companion object {
        private const val NAVER = "NAVER"
        private const val KAKAO = "KAKAO"
    }
}
