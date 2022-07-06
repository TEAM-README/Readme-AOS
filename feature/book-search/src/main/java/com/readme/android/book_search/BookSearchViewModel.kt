package com.readme.android.book_search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.readme.android.core_data.exception.RetrofitFailureStateException
import com.readme.android.core_ui.base.BaseViewModel
import com.readme.android.domain.entity.BookInfo
import com.readme.android.domain.repository.BookSearchRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class BookSearchViewModel @Inject constructor(
    private val bookSearchRepository: BookSearchRepository
) : BaseViewModel() {

    private val _bookSearchList = MutableLiveData<List<BookInfo>>()
    val bookSearchList: LiveData<List<BookInfo>> = _bookSearchList

    private val _bookSearchVisibilityState = MutableLiveData(false)
    val bookSearchVisibilityState: LiveData<Boolean> = _bookSearchVisibilityState

    private val _bookSearchTextState = MutableLiveData(false)
    val bookSearchTextState: LiveData<Boolean> = _bookSearchTextState

    private val _bookSearchCurrentReadState = MutableLiveData(true)
    val bookSearchCurrentReadState: LiveData<Boolean> = _bookSearchCurrentReadState

    var searchWord = MutableLiveData("")

    fun getBookSearchList() {
        viewModelScope.launch {
            bookSearchRepository.getBookSearchList(
                searchWord.value ?: "",
                BOOK_SEARCH_DISPLAY,
                BOOK_SEARCH_START
            )
                .onSuccess {
                    _bookSearchList.postValue(it)
                    if (it.isEmpty()) {
                        _bookSearchTextState.postValue(true)
                        _bookSearchVisibilityState.postValue(true)
                    } else {
                        _bookSearchVisibilityState.postValue(false)
                    }
                }
                .onFailure {
                    it as RetrofitFailureStateException
                    Timber.tag("${this.javaClass.name}_getBookSearchList")
                        .d("message :${it.message} , code :${it.code}")
                }
        }
    }

    fun getRecentReadList() {
        viewModelScope.launch(exceptionHandler) {
            bookSearchRepository.getRecentReadList()
                .onSuccess {
                    _bookSearchList.postValue(it)
                    if (it.isEmpty()) {
                        _bookSearchCurrentReadState.postValue(false)
                        _bookSearchVisibilityState.postValue(true)
                        _bookSearchTextState.postValue(false)
                    }
                }
                .onFailure {
                    Timber.d(it)
                }
        }
    }

    fun setBookSearchVisibilityState(state: Boolean) {
        _bookSearchVisibilityState.postValue(state)
    }

    fun setBookSearchTextState(state: Boolean) {
        _bookSearchTextState.postValue(state)
    }

    fun setBookSearchCurrentReadState(state: Boolean) {
        _bookSearchCurrentReadState.postValue(state)
    }

    companion object {
        const val BOOK_SEARCH_DISPLAY = 50
        const val BOOK_SEARCH_START = 1
    }
}
