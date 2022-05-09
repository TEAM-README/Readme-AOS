package com.readme.android.set_nick_name

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class SetNickNameViewModel @Inject constructor(

) : ViewModel() {

    var nickName = MutableLiveData("")

    private val _nickNameState = MutableLiveData<Int>()
    val nickNameState: LiveData<Int> = _nickNameState

    fun updateNickNameState(state: Int) {
        _nickNameState.value = state
    }
}