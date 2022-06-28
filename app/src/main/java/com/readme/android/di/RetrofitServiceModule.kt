package com.readme.android.di

import com.readme.android.data.remote.service.CheckDuplicateNickNameService
import com.readme.android.data.remote.service.LoginService
import com.readme.android.data.remote.service.NaverBookSearchService
import com.readme.android.data.remote.service.SignUpService
import com.readme.android.di.annotations.NaverBookSearchServer
import com.readme.android.di.annotations.ReadMeServer
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
    fun providesNaverBookSearchService(@NaverBookSearchServer retrofit: Retrofit): NaverBookSearchService =
        retrofit.create(NaverBookSearchService::class.java)

    @Provides
    @Singleton
    fun providesLoginService(@ReadMeServer retrofit: Retrofit): LoginService =
        retrofit.create(LoginService::class.java)

    @Provides
    @Singleton
    fun providesCheckDuplicateNickNameService(@ReadMeServer retrofit: Retrofit): CheckDuplicateNickNameService =
        retrofit.create(CheckDuplicateNickNameService::class.java)

    @Provides
    @Singleton
    fun providesSignUpService(@ReadMeServer retrofit: Retrofit): SignUpService =
        retrofit.create(SignUpService::class.java)
}
