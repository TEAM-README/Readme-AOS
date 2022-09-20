package com.readme.android.auth.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.readme.android.core_ui.util.Event
import com.readme.android.core_ui.util.EventFlow
import com.readme.android.core_ui.util.MutableEventFlow
import com.readme.android.core_ui.util.asEventFlow
import com.readme.android.domain.entity.request.DomainLoginRequest
import com.readme.android.domain.repository.LoginRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginRepository: LoginRepository
) : ViewModel() {
    private val _socialToken = MutableLiveData<String>()
    val socialToken: LiveData<String> = _socialToken

    private lateinit var platform: String

    private val _moveToSetNickname = MutableLiveData<Event<String>>()
    val moveToSetNickname: LiveData<Event<String>> = _moveToSetNickname

    private val _moveToHome = MutableLiveData<Event<Boolean>>()
    val moveToHome: LiveData<Event<Boolean>> = _moveToHome

    private val _loginFailureMessage = MutableLiveData<String>()
    val loginFailureMessage: LiveData<String> = _loginFailureMessage

    private val _isOnboardEnd = MutableEventFlow<Boolean>()
    val isOnboardEnd : EventFlow<Boolean>
        get() = _isOnboardEnd.asEventFlow()

    fun setIsOnboardEnd(isEnd : Boolean) {
        viewModelScope.launch {
            setIsFirstVisit()
            _isOnboardEnd.emit(isEnd)
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
                    saveUserNickName(it.userInfo?.nickname ?: "")
                    saveUserId(it.userInfo?.id ?: throw IllegalStateException("userId 관련오류"))
                    _moveToHome.postValue(Event(true))
                }
            }.onFailure {
                _loginFailureMessage.postValue(it.message)
            }
        }
    }

    fun updateSocialToken(socialToken: String) {
        _socialToken.value = socialToken
    }

    fun saveAccessToken(accessToken: String) {
        loginRepository.saveAccessToken(accessToken)
    }

    fun saveUserNickName(userNickName: String) {
        loginRepository.saveUserNickname(userNickName)
    }

    fun saveUserId(userId: Int) {
        loginRepository.saveUserId(userId)
    }

    fun getAccessToken(): String =
        loginRepository.getAccessToken()

    fun getIsFirstVisit() : Boolean =
        loginRepository.getIsFirstVisit()

    private fun setIsFirstVisit() {
        loginRepository.setIsFirstVisit(false)
    }

    fun updatePlatform(platform: String) {
        this.platform = platform
    }
}
