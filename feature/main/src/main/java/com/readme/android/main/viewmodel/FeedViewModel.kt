package com.readme.android.main.viewmodel

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.readme.android.core_ui.base.BaseViewModel
import com.readme.android.core_ui.util.Event
import com.readme.android.core_ui.util.MutableEventFlow
import com.readme.android.core_ui.util.asEventFlow
import com.readme.android.domain.entity.BookInfo
import com.readme.android.domain.entity.FeedInfo
import com.readme.android.domain.repository.FeedRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FeedViewModel @Inject constructor(
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
                    serverResponseStatus(SUCCESS)
                }.onFailure {
                    serverResponseStatus(FAIL)
                }
        }
    }

    private fun categoryListToString(): String =
        selectedCategoryChip.value?.joinToString(SEPARATOR) ?: ""

    /** FeedDetailActivity */
    private val feedId = MutableLiveData<Int>()

    private val _feed = MutableLiveData<FeedInfo>()
    val feed: LiveData<FeedInfo> = _feed

    private val _bookInfo = MutableLiveData<BookInfo>()
    val bookInfo: LiveData<BookInfo> = _bookInfo

    fun setFeedId(feedId: Int) {
        this.feedId.value = feedId
    }

    fun getIsMyFeed(): Boolean = feed.value?.isMyFeed ?: throw IllegalStateException()

    fun getWriterNickname(): String =
        feed.value?.nickname ?: throw IllegalStateException("writer nickname cannot be null")

    fun getFeedId(): Int =
        feed.value?.id ?: throw IllegalStateException("feed id cannot be null")

    fun getDetailFeedInfo() {
        viewModelScope.launch(exceptionHandler) {
            feedRepository.getDetailFeed(feedId.value ?: throw IllegalStateException())
                .onSuccess {
                    _feed.value = it.feed
                    _bookInfo.value = it.bookInfo
                }
                .onFailure { Log.d(TAG, "getDetailFeedInfo: $it") }
        }
    }

    /** server event */
    private val _isNetworkCorrespondenceEnd = MutableLiveData<Event<String>>()
    val isNetworkCorrespondenceEnd: MutableLiveData<Event<String>>
        get() = _isNetworkCorrespondenceEnd

    private fun serverResponseStatus(string: String) {
        _isNetworkCorrespondenceEnd.value = Event(string)
    }

    companion object {
        const val SEPARATOR = ","
        const val SUCCESS = "SUCCESS"
        const val FAIL = "FAIL"
    }
}
