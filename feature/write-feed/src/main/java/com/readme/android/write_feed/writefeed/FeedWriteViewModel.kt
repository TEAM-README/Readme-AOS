package com.readme.android.write_feed.writefeed

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.readme.android.core_ui.base.BaseViewModel
import com.readme.android.core_ui.constant.FeedWriteFragmentList
import com.readme.android.core_ui.constant.FeedWriteFragmentList.CHOOSE_CATEGORY
import com.readme.android.domain.repository.FeedWriteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FeedWriteViewModel @Inject constructor(
    private val feedWriteRepository: FeedWriteRepository
) : BaseViewModel() {

    private val _currentFragment = MutableLiveData(CHOOSE_CATEGORY)
    val currentFragment: LiveData<FeedWriteFragmentList> = _currentFragment

    private val _nickName = MutableLiveData<String>()
    val nickName: LiveData<String> = _nickName

    private val _title = MutableLiveData<String>()
    val title: LiveData<String> = _title

    private val _author = MutableLiveData<String>()
    val author: LiveData<String> = _author

    private val _image = MutableLiveData<String>()
    val image: LiveData<String> = _image

    lateinit var isbn: String
        private set

    lateinit var subIsbn: String
        private set

    private val _category = MutableLiveData("")
    val category: LiveData<String> = _category

    val impressiveSentence = MutableLiveData("")

    val feeling = MutableLiveData("")

    private val _keyBoardState = MutableLiveData<Boolean>(true)
    val keyBoardState: LiveData<Boolean> = _keyBoardState

    fun getUserNickName() {
        _nickName.postValue(feedWriteRepository.getUserNickName())
    }

    fun updateCurrentFragment(currentFragment: FeedWriteFragmentList) {
        _currentFragment.postValue(currentFragment)
    }

    fun initBookInfo(title: String, author: String, image: String, isbn: String, subIsbn: String) {
        _title.postValue(title)
        _author.postValue(author)
        _image.postValue(image)
        this.isbn = isbn
        this.subIsbn = subIsbn
    }

    fun setKeyBoardState(state: Boolean) {
        _keyBoardState.value = state
    }

    fun updateCategory(category: String) {
        _category.value = category
    }
}
