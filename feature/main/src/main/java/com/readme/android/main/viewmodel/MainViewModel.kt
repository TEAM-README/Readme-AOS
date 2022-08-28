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

    val isCategorySelected = MutableLiveData<Boolean>()

    private var _homeFeedInfoList = MutableLiveData<List<FeedInfo>>()
    val homeFeedInfoList: LiveData<List<FeedInfo>> = _homeFeedInfoList

    private val _selectedCategoryChip = MutableLiveData<List<String>>()
    val selectedCategoryChip: LiveData<List<String>> = _selectedCategoryChip

    private val _selectedCategoryString = MutableLiveData<String>()
    val selectedCategoryString: LiveData<String> = _selectedCategoryString

    fun setSelectedCategoryChip(list: List<String>) {
        _selectedCategoryChip.value = list
    }

    fun setIsCategorySelected() {
        isCategorySelected.value = _selectedCategoryChip.value?.size != 0
    }

    private fun setSelectedCategory(string: String) {
        _selectedCategoryString.value = string
    }

    fun updateSelectedCategoryString() {
        val categoryList: List<String> = selectedCategoryChip.value?.toList() ?: return
        val message = when (selectedCategoryChip.value?.size) {
            0 -> "관심있는 카테고리"
            1 -> categoryList[0]
            2 -> "${categoryList[0]}, ${categoryList[1]}"
            else -> "${categoryList[0]}, ${categoryList[1]} 외 ${categoryList.size - 2}개"
        }
        setSelectedCategory(message)
    }

    fun getHomeFeed() {
        viewModelScope.launch(exceptionHandler) {
            feedRepository.getHomeFeed(categoryListToString())
                .onSuccess {
                    _homeFeedInfoList.value = it.feedListInfo
                }.onFailure {
                    Log.d(TAG, "getHomeFeed failure: $it")
                }
        }
    }

    fun deleteFeed(feedId: Int) {
        viewModelScope.launch(exceptionHandler) {
            feedRepository.deleteFeed(feedId)
                .onSuccess {
                    Log.d(TAG, "deleteFeed: $it")
                    // todo detailActivity, HomeFragment, MyPage각각 이벤트 처리 해야함
                }.onFailure {
                    Log.d(TAG, "deleteFeed: $it")
                }

        }
    }

    private fun categoryListToString(): String =
        selectedCategoryChip.value?.joinToString(SEPARATOR) ?: ""


    companion object {
        const val SEPARATOR = ","
    }
}
