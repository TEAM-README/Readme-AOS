package com.readme.android.write_feed.postfeed

import android.content.ContentValues
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.readme.android.core_ui.base.BaseViewModel
import com.readme.android.core_ui.util.Event
import com.readme.android.domain.entity.BookInfo
import com.readme.android.domain.entity.request.DomainPostFeedRequest
import com.readme.android.domain.repository.FeedWriteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@HiltViewModel
class PostFeedViewModel @Inject constructor(
    private val feedWriteRepository: FeedWriteRepository
) : BaseViewModel() {

    private val _wholeCategoryString = MutableLiveData<String>()
    val wholeCategoryString: LiveData<String> = _wholeCategoryString

    private val _representCategoryString = MutableLiveData<String>()
    val representCategoryString: LiveData<String> = _representCategoryString

    private val _impressiveSentence = MutableLiveData<String>()
    val impressiveSentence: LiveData<String> = _impressiveSentence

    private val _feeling = MutableLiveData<String>()
    val feeling: LiveData<String> = _feeling

    private val _bookInfo = MutableLiveData<BookInfo>()
    val bookInfo: LiveData<BookInfo> = _bookInfo

    private val _nickName = MutableLiveData<String>()
    val nickName: LiveData<String> = _nickName

    private val _writeFeedDate = MutableLiveData<String>()
    val writeFeedDate: LiveData<String> = _writeFeedDate

    private val _postFeedState = MutableLiveData<Event<Boolean>>()
    val postFeedState: LiveData<Event<Boolean>> = _postFeedState

    fun getUserNickName() {
        _nickName.value = feedWriteRepository.getUserNickName()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun initWriteFeedDate() {
        val dateFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd")
        _writeFeedDate.value = LocalDate.now().format(dateFormatter)
    }

    fun initPostFeedData(
        wholeCategoryString: String,
        representCategoryString: String,
        impressiveSentence: String,
        feeling: String,
        bookInfo: BookInfo
    ) {
        _wholeCategoryString.value = wholeCategoryString
        _representCategoryString.value = representCategoryString
        _impressiveSentence.value = impressiveSentence
        _feeling.value = feeling
        _bookInfo.value = bookInfo
    }

    fun postFeed() {
        viewModelScope.launch {
            feedWriteRepository.postFeed(
                DomainPostFeedRequest(
                    categoryName = wholeCategoryString.value ?: "",
                    impressiveSentence = impressiveSentence.value ?: "",
                    feeling = feeling.value ?: "",
                    bookInfo = bookInfo.value ?: throw IllegalStateException()
                )
            ).onSuccess {
                if (it){
                    _postFeedState.postValue(Event(true))
                }
            }.onFailure {
                Timber.d(it)
            }
        }
    }
}
