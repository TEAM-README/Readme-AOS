package com.readme.android.set_nick_name

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.readme.android.core_ui.constant.SetNickNameConstant
import com.readme.android.core_ui.constant.SetNickNameConstant.ALLOWED_NICKNAME
import com.readme.android.core_ui.constant.SetNickNameConstant.DUPLICATE_NICKNAME
import com.readme.android.core_ui.util.Event
import com.readme.android.domain.entity.request.DomainSignUpRequest
import com.readme.android.domain.repository.SignUpRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class SetNickNameViewModel @Inject constructor(
    private val signUpRepository: SignUpRepository
) : ViewModel() {

    var nickName = MutableLiveData("")

    private val _nickNameState = MutableLiveData<SetNickNameConstant>()
    val nickNameState: LiveData<SetNickNameConstant> = _nickNameState

    private val _moveToHome = MutableLiveData<Event<Boolean>>()
    val moveToHome: LiveData<Event<Boolean>> = _moveToHome

    fun updateNickNameState(state: SetNickNameConstant) {
        _nickNameState.value = state
    }

    fun checkDuplicateNickName() {
        viewModelScope.launch {
            signUpRepository.checkDuplicateNickName(nickName.value ?: throw IllegalStateException())
                .onSuccess {
                    if (it) {
                        _nickNameState.postValue(ALLOWED_NICKNAME)
                    } else {
                        _nickNameState.postValue(DUPLICATE_NICKNAME)
                    }
                }
                .onFailure {
                    Timber.d(it)
                }
        }
    }

    fun postSignUp(platform: String, socialToken: String) {
        viewModelScope.launch {
            signUpRepository.postSignUp(
                DomainSignUpRequest(
                    platform = platform,
                    socialToken = socialToken,
                    nickname = nickName.value ?: ""
                )
            ).onSuccess {
                signUpRepository.saveAccessToken(it.accessToken)
                signUpRepository.saveUserNickname(it.userInfo.nickname)
                signUpRepository.saveUserId(it.userInfo.id)
                _moveToHome.postValue(Event(true))
            }.onFailure {
                Timber.d(it)
            }
        }
    }
}
