package com.readme.android.auth.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.navercorp.nid.NaverIdLoginSDK
import com.navercorp.nid.oauth.NidOAuthLogin
import com.navercorp.nid.oauth.OAuthLoginCallback
import com.navercorp.nid.profile.NidProfileCallback
import com.navercorp.nid.profile.data.NidProfileResponse
import com.readme.android.core_ui.util.Event
import com.readme.android.domain.entity.request.DomainLoginRequest
import com.readme.android.domain.repository.LoginRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject
import kotlin.properties.Delegates

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginRepository: LoginRepository
) : ViewModel() {
    lateinit var oAuthLoginCallback: OAuthLoginCallback
        private set

    private val _socialToken = MutableLiveData<String>()
    val socialToken: LiveData<String> = _socialToken

    private lateinit var platform: String

    private val _moveToSetNickname = MutableLiveData<Event<String>>()
    val moveToSetNickname: LiveData<Event<String>> = _moveToSetNickname

    private val _moveToHome = MutableLiveData<Event<Boolean>>()
    val moveToHome: LiveData<Event<Boolean>> = _moveToHome

    private val _loginFailureMessage = MutableLiveData<String>()
    val loginFailureMessage: LiveData<String> = _loginFailureMessage


    fun naverSetOAuthLoginCallback() {
        oAuthLoginCallback = object : OAuthLoginCallback {
            override fun onSuccess() {
                _socialToken.postValue(NaverIdLoginSDK.getAccessToken())
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

    fun postLogin() {
        viewModelScope.launch {
            loginRepository.postLogin(
                DomainLoginRequest(
                    platform = platform,
                    socialToken = socialToken.value ?: ""
                )
            ).onSuccess {
                if (it.isNewUser) {
                    _moveToSetNickname.postValue(Event(platform))
                } else {
                    saveAccessToken(it.accessToken ?: "")
                    _moveToHome.postValue(Event(true))
                }
            }.onFailure {
                _loginFailureMessage.postValue(it.message)
            }
        }
    }

    fun saveAccessToken(accessToken: String) {
        loginRepository.saveAccessToken(accessToken)
    }

    fun saveUserNickName(userNickName: String) {
        loginRepository.saveUserNickname(userNickName)
    }

    fun getAccessToken(): String =
        loginRepository.getAccessToken()


    fun updatePlatform(platform: String) {
        this.platform = platform
    }

}
