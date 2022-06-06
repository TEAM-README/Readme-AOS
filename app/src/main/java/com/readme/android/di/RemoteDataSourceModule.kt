package com.readme.android.di

import com.readme.android.data.remote.datasource.RemoteBookSearchDataSource
import com.readme.android.data.remote.datasource.RemoteBookSearchDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RemoteDataSourceModule {

    @Binds
    @Singleton
    fun bindsRemoteBookSearchDataSource(source: RemoteBookSearchDataSourceImpl): RemoteBookSearchDataSource
}
