package com.readme.android.write_feed

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.readme.android.core_ui.base.BaseViewModel
import com.readme.android.write_feed.constant.FeedWriteFragmentList
import com.readme.android.write_feed.constant.FeedWriteFragmentList.CHOOSE_CATEGORY
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FeedWriteViewModel @Inject constructor(

) : BaseViewModel(){

    private val _currentFragment = MutableLiveData(CHOOSE_CATEGORY)
    val currentFragment: LiveData<FeedWriteFragmentList> = _currentFragment


}
