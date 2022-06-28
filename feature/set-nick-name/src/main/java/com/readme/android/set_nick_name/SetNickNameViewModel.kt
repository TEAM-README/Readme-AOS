package com.readme.android.set_nick_name

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.readme.android.core_ui.constant.SetNickNameConstant
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SetNickNameViewModel @Inject constructor(

) : ViewModel() {

    var nickName = MutableLiveData("")

    private val _nickNameState = MutableLiveData<SetNickNameConstant>()
    val nickNameState: LiveData<SetNickNameConstant> = _nickNameState

    fun updateNickNameState(state: SetNickNameConstant) {
        _nickNameState.value = state
    }
}
