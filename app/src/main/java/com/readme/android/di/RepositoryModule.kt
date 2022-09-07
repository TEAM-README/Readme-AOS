package com.readme.android.di

import com.readme.android.data.repository.*
import com.readme.android.domain.repository.*
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

    @Binds
    @Singleton
    fun bindsFeedWriteRepository(repository: FeedWriteRepositoryImpl): FeedWriteRepository

    @Binds
    @Singleton
    fun bindsFeedRepository(repository: FeedRepositoryImpl): FeedRepository

    @Binds
    @Singleton
    fun bindsUserRepository(repository: UserRepositoryImpl): UserRepository
}
