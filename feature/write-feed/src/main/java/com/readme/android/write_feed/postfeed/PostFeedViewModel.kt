package com.readme.android.write_feed.postfeed

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.readme.android.core_ui.base.BaseViewModel
import com.readme.android.domain.entity.BookInfo
import com.readme.android.domain.repository.FeedWriteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
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

    fun getUserNickName() {
        _nickName.postValue(feedWriteRepository.getUserNickName())
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getWriteFeedDate() {
        val dateFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd")
        _writeFeedDate.value = LocalDate.parse(LocalDate.now().toString(), dateFormatter).toString()
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
}
