package com.readme.android.di

import com.readme.android.data.remote.service.NaverBookSearchService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitServiceModule {

    @Provides
    @Singleton
    fun providesNaverBookSearchService(retrofit: Retrofit): NaverBookSearchService =
        retrofit.create(NaverBookSearchService::class.java)

}
