package com.readme.android.auth.ui.socialloginmanager

import android.content.Context
import com.navercorp.nid.NaverIdLoginSDK
import com.navercorp.nid.oauth.NidOAuthLogin
import com.navercorp.nid.oauth.OAuthLoginCallback
import com.navercorp.nid.profile.NidProfileCallback
import com.navercorp.nid.profile.data.NidProfileResponse
import com.readme.android.auth.BuildConfig.X_NAVER_CLIENT_ID
import com.readme.android.auth.BuildConfig.X_NAVER_CLIENT_SECRET
import dagger.hilt.android.qualifiers.ActivityContext
import timber.log.Timber
import javax.inject.Inject

class NaverLoginManager @Inject constructor(
    @ActivityContext private val context: Context
) {
    lateinit var oAuthLoginCallback: OAuthLoginCallback
        private set

    fun naverSetOAuthLoginCallback(updateSocialToken: (String) -> Unit) {
        oAuthLoginCallback = object : OAuthLoginCallback {
            override fun onSuccess() {
                updateSocialToken(NaverIdLoginSDK.getAccessToken() ?: "")
                NidOAuthLogin().callProfileApi(object : NidProfileCallback<NidProfileResponse> {
                    override fun onSuccess(result: NidProfileResponse) {
//                        이제 이름 등등 받아서 어따 넘기는 로직
                    }

                    override fun onError(errorCode: Int, message: String) {
                        Timber.d("$message NidProfileCallback 부분에서의 오류 onError")
                    }

                    override fun onFailure(httpStatus: Int, message: String) {
                        Timber.d("$message NidProfileCallback 부분에서의 오류 onFailure")
                    }
                })
            }

            override fun onError(errorCode: Int, message: String) {
                Timber.d("$message OAuthLoginCallback 부분에서의 오류 onError")
            }

            override fun onFailure(httpStatus: Int, message: String) {
                Timber.d("$message OAuthLoginCallback 부분에서의 오류 onFailure")
            }
        }
    }

    fun startNaverLogin(updateSocialToken: (String) -> Unit) {
        naverSetOAuthLoginCallback { updateSocialToken(it) }
        NaverIdLoginSDK.initialize(
            context,
            X_NAVER_CLIENT_ID,
            X_NAVER_CLIENT_SECRET,
            CLIENT_NAME
        )
        NaverIdLoginSDK.authenticate(context, oAuthLoginCallback)
    }

    companion object {
        private const val CLIENT_NAME = "ReadMe"
    }
}
