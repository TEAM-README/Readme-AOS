package com.readme.android.main.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.readme.android.core.util.MutableEventFlow
import com.readme.android.core.util.asEventFlow
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {
    private val _isMyFeed = MutableEventFlow<Boolean>()
    val isMyFeed
        get() = _isMyFeed.asEventFlow()

    // 피드가 내 피드인지 남피드인지 설정해주는 메소드
    private fun setIsMyFeed(isMyFeed: Boolean) {
        viewModelScope.launch {
            _isMyFeed.emit(isMyFeed)
        }
    }
}
