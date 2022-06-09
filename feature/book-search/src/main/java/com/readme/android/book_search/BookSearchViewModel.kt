package com.readme.android.book_search

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.readme.android.core.exception.RetrofitFailureStateException
import com.readme.android.domain.entity.BookInfo
import com.readme.android.domain.repository.BookSearchRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import java.lang.Exception
import java.net.HttpRetryException
import javax.inject.Inject

@HiltViewModel
class BookSearchViewModel @Inject constructor(
    private val bookSearchRepository: BookSearchRepository
) : ViewModel() {

    private val _bookSearchList = MutableLiveData<List<BookInfo>>()
    val bookSearchList: LiveData<List<BookInfo>> = _bookSearchList

    var searchWord = MutableLiveData("")

    fun getBookSearchList() {
        viewModelScope.launch {
            bookSearchRepository.getBookSearchList(searchWord.value ?: "", BOOK_SEARCH_DISPLAY, BOOK_SEARCH_START)
                .onSuccess {
                    _bookSearchList.postValue(it)
                }
                .onFailure {
                    it as RetrofitFailureStateException
                    Timber.tag("${this.javaClass.name}_getBookSearchList").d("message :${it.message} , code :${it.code}")
                }
        }
    }

    companion object{
        const val BOOK_SEARCH_DISPLAY = 50
        const val BOOK_SEARCH_START = 1
    }
}
