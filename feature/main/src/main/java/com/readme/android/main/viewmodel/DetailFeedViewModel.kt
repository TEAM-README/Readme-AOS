package com.readme.android.main.viewmodel

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.readme.android.core_ui.base.BaseViewModel
import com.readme.android.domain.entity.FeedInfo
import com.readme.android.domain.repository.FeedRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.IllegalStateException

@HiltViewModel
class DetailFeedViewModel @Inject constructor(
    private val feedRepository: FeedRepository
) : BaseViewModel() {

    private val _feedId = MutableLiveData<Int>()
    val feedId: LiveData<Int> = _feedId

    private val _feed = MutableLiveData<FeedInfo>()
    val feed: LiveData<FeedInfo> = _feed

    fun setFeedId(feedId: Int) {
        _feedId.value = feedId
    }

    fun getDetailFeedInfo() {
        viewModelScope.launch(exceptionHandler) {
            feedRepository.getDetailFeed(feedId.value ?: throw IllegalStateException())
                .onSuccess { Log.d(TAG, "getDetailFeedInfo: ${it.feed}") }
                .onFailure { Log.d(TAG, "getDetailFeedInfo: ${it}") }
                .run { Log.d(TAG, "getDetailFeedInfo:  서버통신완료") }
        }
    }
}