package com.readme.android.core_ui.base

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.readme.android.core_ui.util.Event
import dagger.hilt.android.EntryPointAccessors
import dagger.hilt.android.qualifiers.ActivityContext
import kotlinx.coroutines.CoroutineExceptionHandler
import java.security.cert.CertificateException

abstract class  BaseViewModel : ViewModel() {

    private val _moveToLogin = MutableLiveData<Event<Boolean>>()
    val moveToLogin: LiveData<Event<Boolean>> = _moveToLogin

    val exceptionHandler = CoroutineExceptionHandler{ _, throwable ->
        when(throwable){
            is CertificateException -> _moveToLogin.postValue(Event(true))
        }
    }
}
