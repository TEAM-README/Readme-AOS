package com.readme.android.di

import com.readme.android.data.remote.repository.BookSearchRepositoryImpl
import com.readme.android.domain.repository.BookSearchRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    @Singleton
    fun bindsBookSearchRepository(repository: BookSearchRepositoryImpl): BookSearchRepository
}
