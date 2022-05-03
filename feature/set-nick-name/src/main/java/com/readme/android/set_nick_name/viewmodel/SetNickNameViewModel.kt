package com.readme.android.set_nick_name.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class SetNickNameViewModel @Inject constructor(

) : ViewModel() {

    var nickName = MutableLiveData("")

    private val _nickNameState = MutableLiveData("")
    val nickNameState: LiveData<String> = _nickNameState


}