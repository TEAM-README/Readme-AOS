package com.readme.android.write_feed

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

    fun getUserNickName() {
        _nickName.postValue(feedWriteRepository.getUserNickName())
    }

    fun updateCurrentFragment(currentFragment: FeedWriteFragmentList){
        _currentFragment.postValue(currentFragment)
    }
}
