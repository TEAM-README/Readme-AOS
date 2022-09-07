package com.readme.android.main.ui.mypage

import androidx.lifecycle.viewModelScope
import com.readme.android.core_ui.base.BaseViewModel
import com.readme.android.core_ui.util.EventFlow
import com.readme.android.core_ui.util.MutableEventFlow
import com.readme.android.core_ui.util.UiState
import com.readme.android.core_ui.util.asEventFlow
import com.readme.android.domain.entity.MyPageInfo
import com.readme.android.domain.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyPageViewModel @Inject constructor(
    private val userRepository: UserRepository,
) : BaseViewModel() {
    private val _isLogOutEvent = MutableEventFlow<LogOutState>()
    val isLogOutEvent: EventFlow<LogOutState>
        get() = _isLogOutEvent.asEventFlow()

    private val _isFeedEmpty = MutableEventFlow<Boolean>()
    val isFeedEmpty: EventFlow<Boolean>
        get() = _isFeedEmpty.asEventFlow()

    private val _myPageUiState: MutableStateFlow<UiState<MyPageInfo>> =
        MutableStateFlow(UiState.Loading)
    val myPageUiState: StateFlow<UiState<MyPageInfo>>
        get() = _myPageUiState.asStateFlow()

    fun getMyPageInfo() {
        viewModelScope.launch(exceptionHandler) {
            _myPageUiState.value = UiState.Loading
            userRepository.getMyPageInfo()
                .onSuccess {
                    _myPageUiState.value = UiState.Success(it)
                    _isFeedEmpty.emit(it.feedList.isEmpty())
                }
                .onFailure {
                    _myPageUiState.value = UiState.Failure(it.message)
                }
        }
    }

    fun deleteUser() {
        viewModelScope.launch(exceptionHandler) {
            userRepository.deleteUser()
                .onSuccess {
                    clearUserInfo()
                }
                .onFailure {
                    _isLogOutEvent.emit(LogOutState.Failure(it.message))
                }
        }
    }

    suspend fun clearUserInfo() {
        userRepository.clearUserInfo()
        _isLogOutEvent.emit(LogOutState.Success)
    }
}

sealed interface LogOutState {
    object Success : LogOutState
    data class Failure(val msg: String?) : LogOutState
}
