package com.readme.android.set_nick_name

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.readme.android.core_ui.constant.SetNickNameConstant.ALLOWED_NICKNAME
import com.readme.android.core_ui.constant.SetNickNameConstant
import com.readme.android.core_ui.constant.SetNickNameConstant.DUPLICATE_NICKNAME
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
}
