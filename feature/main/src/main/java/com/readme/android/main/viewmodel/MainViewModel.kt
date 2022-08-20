package com.readme.android.main.viewmodel

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.readme.android.core_ui.base.BaseViewModel
import com.readme.android.core_ui.util.MutableEventFlow
import com.readme.android.core_ui.util.asEventFlow
import com.readme.android.domain.entity.FeedInfo
import com.readme.android.domain.repository.FeedRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val feedRepository: FeedRepository
) : BaseViewModel() {
    private val _isMyFeed = MutableEventFlow<Boolean>()
    val isMyFeed
        get() = _isMyFeed.asEventFlow()

    private val isCategorySelected = MutableLiveData(true)
    private val selectedCategory = MutableLiveData("에세이, 경제/경영 외 2개") // 수정예정
    private val selectedCategoryChip = MutableLiveData<List<String>>()
    private var _homeFeedInfoList = MutableLiveData<List<FeedInfo>>()
    val homeFeedInfoList: LiveData<List<FeedInfo>> = _homeFeedInfoList

    fun setSelectedCategoryChip(list: List<String>) {
        selectedCategoryChip.value = list
    }

    fun getIsCategorySelected(): Boolean = isCategorySelected.value == true

    fun getSelectedCategory() = selectedCategory.value

    fun getHomeFeed() {
        Log.d(TAG, "getHomeFeed: ${categoryListToString()}")
        viewModelScope.launch(exceptionHandler) {
            feedRepository.getHomeFeed(categoryListToString())
                .onSuccess {
                    _homeFeedInfoList.value = it.feedInfos
                    Log.d(TAG, "getHomeFeed success: $it")
                }.onFailure {
                    Log.d(TAG, "getHomeFeed failure: $it")
                }
        }
    }

    private fun categoryListToString(): String =
        selectedCategoryChip.value?.joinToString(SEPARATOR) ?: ""

    // 피드가 내 피드인지 남피드인지 설정해주는 메소드
    private fun setIsMyFeed(isMyFeed: Boolean) {
        viewModelScope.launch {
            _isMyFeed.emit(isMyFeed)
        }
    }

    companion object {
        const val SEPARATOR = ","
    }
}
