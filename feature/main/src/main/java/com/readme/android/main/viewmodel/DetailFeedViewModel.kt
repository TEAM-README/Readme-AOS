package com.readme.android.main.viewmodel

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.readme.android.core_ui.base.BaseViewModel
import com.readme.android.domain.entity.BookInfo
import com.readme.android.domain.entity.FeedInfo
import com.readme.android.domain.repository.FeedRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailFeedViewModel @Inject constructor(
    private val feedRepository: FeedRepository
) : BaseViewModel() {

    private val _feedId = MutableLiveData<Int>()
    val feedId: LiveData<Int> = _feedId

    private val _feed = MutableLiveData<FeedInfo>()
    val feed: LiveData<FeedInfo> = _feed

    private val _bookInfo = MutableLiveData<BookInfo>()
    val bookInfo: LiveData<BookInfo> = _bookInfo

    fun setFeedId(feedId: Int) {
        _feedId.value = feedId
    }

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
}