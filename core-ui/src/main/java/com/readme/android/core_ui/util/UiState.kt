package com.readme.android.core_ui.util

sealed interface UiState<out T> {
    object Loading : UiState<Nothing>

    data class Success<T>(
        val data: T,
    ) : UiState<T>

    data class Failure(
        val msg: String?,
    ) : UiState<Nothing>
}
