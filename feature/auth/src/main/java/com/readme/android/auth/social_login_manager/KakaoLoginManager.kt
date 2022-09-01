package com.readme.android.auth.social_login_manager

import android.content.Context
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.model.ClientError
import com.kakao.sdk.common.model.ClientErrorCause
import com.kakao.sdk.user.UserApiClient
import dagger.hilt.android.qualifiers.ActivityContext
import timber.log.Timber
import javax.inject.Inject

class KakaoLoginManager @Inject constructor(
    @ActivityContext private val context: Context
) {
    private lateinit var kakaoLoginState: KaKaoLoginState
    private lateinit var kakaoLoginCallback: (OAuthToken?, Throwable?) -> Unit

    fun startKakaoLogin(
        updateSocialToken: (String) -> Unit
    ) {

        kakaoLoginState = getKaKaoLoginState()
        kakaoLoginCallback = getLoginCallback(updateSocialToken)

        when (kakaoLoginState) {
            KaKaoLoginState.KAKAO_TALK_LOGIN -> onKakaoTalkLogin(updateSocialToken)
            KaKaoLoginState.KAKAO_ACCOUNT_LOGIN -> onKakaoAccountLogin()
        }
    }

    private fun getKaKaoLoginState(): KaKaoLoginState =
        if (UserApiClient.instance.isKakaoTalkLoginAvailable(context))
            KaKaoLoginState.KAKAO_TALK_LOGIN
        else KaKaoLoginState.KAKAO_ACCOUNT_LOGIN


    private fun getLoginCallback(updateSocialToken: (String) -> Unit): (OAuthToken?, Throwable?) -> Unit {
        return { token, error ->
            if (error != null)
                Timber.d("${error.message} 카카오 계정으로 로그인 실패")
            else if (token != null)
                updateSocialToken(token.accessToken)
        }
    }

    private fun onKakaoTalkLogin(updateSocialToken: (String) -> Unit) {
        UserApiClient.instance.loginWithKakaoTalk(context) { token, error ->
            if (error != null) {
                // 카카오톡으로 로그인 실패
                if (error is ClientError && error.reason == ClientErrorCause.Cancelled)
                    return@loginWithKakaoTalk
                onKakaoAccountLogin()
            } else if (token != null)
                updateSocialToken(token.accessToken)
        }
    }

    private fun onKakaoAccountLogin() {
        UserApiClient.instance.loginWithKakaoAccount(context, callback = kakaoLoginCallback)
    }
}

enum class KaKaoLoginState {
    KAKAO_TALK_LOGIN, KAKAO_ACCOUNT_LOGIN
}
