package com.readme.android.main.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.readme.android.core_ui.util.MutableEventFlow
import com.readme.android.core_ui.util.asEventFlow
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {
    private val _isMyFeed = MutableEventFlow<Boolean>()
    val isMyFeed
        get() = _isMyFeed.asEventFlow()

    private val isCategorySelected = MutableLiveData(true)
    private val selectedCategory = MutableLiveData("에세이, 경제/경영 외 2개") // 수정예정

    fun getIsCategorySelected(): Boolean = isCategorySelected.value == true
    fun getSelectedCategory() = selectedCategory.value

    // 피드가 내 피드인지 남피드인지 설정해주는 메소드
    private fun setIsMyFeed(isMyFeed: Boolean) {
        viewModelScope.launch {
            _isMyFeed.emit(isMyFeed)
        }
    }
}
