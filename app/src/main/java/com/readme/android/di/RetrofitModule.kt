package com.readme.android.di

import com.readme.android.data.remote.calladapter.CustomCallAdapterFactory
import com.readme.android.di.annotations.NaverBookSearchServer
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    const val X_NAVER_CLIENT_ID = "acNJpGxulL56tNkuwd6X"
    const val X_NAVER_CLIENT_SECRET = "D6O_BbQncI"

    @Provides
    @Singleton
    @NaverBookSearchServer
    fun providesInterceptor(): Interceptor =
        Interceptor { chain ->
            with(chain) {
                proceed(
                    request()
                        .newBuilder()
                        .addHeader("X-Naver-Client-Id", X_NAVER_CLIENT_ID)
                        .addHeader("X-Naver-Client-Secret", X_NAVER_CLIENT_SECRET)
                        .build()
                )
            }
        }

    @Provides
    @Singleton
    @NaverBookSearchServer
    fun providesOkHttpClient(@NaverBookSearchServer interceptor: Interceptor): OkHttpClient =
        OkHttpClient.Builder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .addInterceptor(interceptor)
            .build()

    @Provides
    @Singleton
    @NaverBookSearchServer
    fun providesRetrofit(@NaverBookSearchServer okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl("https://openapi.naver.com/v1/search/")
            .client(okHttpClient)
            .addCallAdapterFactory(CustomCallAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
}
