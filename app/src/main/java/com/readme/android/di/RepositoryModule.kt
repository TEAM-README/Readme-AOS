package com.readme.android.di

import com.readme.android.data.repository.BookSearchRepositoryImpl
import com.readme.android.data.repository.LoginRepositoryImpl
import com.readme.android.data.repository.SignUpRepositoryImpl
import com.readme.android.domain.repository.BookSearchRepository
import com.readme.android.domain.repository.LoginRepository
import com.readme.android.domain.repository.SignUpRepository
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

    @Binds
    @Singleton
    fun bindsLoginRepository(repository: LoginRepositoryImpl): LoginRepository

    @Binds
    @Singleton
    fun bindsSignUpRepository(repository: SignUpRepositoryImpl): SignUpRepository
}
