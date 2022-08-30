package com.readme.android.di

import com.readme.android.data.remote.datasource.*
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

    @Binds
    @Singleton
    fun bindsRemoteLoginDataSource(source: RemoteLoginDataSourceImpl): RemoteLoginDataSource

    @Binds
    @Singleton
    fun bindsRemoteCheckDuplicateNickNameDataSource(source: RemoteSignUpDataSourceImpl): RemoteSignUpDataSource

    @Binds
    @Singleton
    fun bindsRemoteHomeFeedDataSource(source: RemoteFeedDataSourceImpl): RemoteFeedDataSource

}
