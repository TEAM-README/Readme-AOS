package com.readme.android.auth.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import com.navercorp.nid.NaverIdLoginSDK
import com.navercorp.nid.oauth.NidOAuthLogin
import com.navercorp.nid.oauth.OAuthLoginCallback
import com.navercorp.nid.profile.NidProfileCallback
import com.navercorp.nid.profile.data.NidProfileResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(

) : ViewModel() {

    lateinit var oAuthLoginCallback: OAuthLoginCallback
        private set

    fun setOAuthLoginCallback() {
        oAuthLoginCallback = object : OAuthLoginCallback {
            override fun onSuccess() {
                Log.d("이창환","${NaverIdLoginSDK.getAccessToken()}")

                NidOAuthLogin().callProfileApi(object :NidProfileCallback<NidProfileResponse>{
                    override fun onSuccess(result: NidProfileResponse) {
//                        TODO("Not yet implemented")
                    }

                    override fun onError(errorCode: Int, message: String) {
//                        TODO("Not yet implemented")
                    }

                    override fun onFailure(httpStatus: Int, message: String) {
//                        TODO("Not yet implemented")
                    }
                })
            }

            override fun onError(errorCode: Int, message: String) {
//                TODO("Not yet implemented")
            }

            override fun onFailure(httpStatus: Int, message: String) {
//                TODO("Not yet implemented")
            }
        }
    }


}
