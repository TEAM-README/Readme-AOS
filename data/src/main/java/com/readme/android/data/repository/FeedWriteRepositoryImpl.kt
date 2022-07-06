package com.readme.android.data.repository

import com.readme.android.data.local.datasource.LocalPreferenceUserDataSource
import com.readme.android.domain.repository.FeedWriteRepository
import javax.inject.Inject

class FeedWriteRepositoryImpl @Inject constructor(
    private val localPreferenceUserDataSource: LocalPreferenceUserDataSource
) : FeedWriteRepository {
    override fun getUserNickName(): String =
        localPreferenceUserDataSource.getUserNickname()
}
