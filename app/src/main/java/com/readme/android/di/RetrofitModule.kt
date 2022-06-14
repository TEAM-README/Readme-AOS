package com.readme.android.di

import com.readme.android.BuildConfig
import com.readme.android.BuildConfig.X_NAVER_CLIENT_ID
import com.readme.android.BuildConfig.X_NAVER_CLIENT_SECRET
import com.readme.android.data.remote.calladapter.CustomCallAdapterFactory
import com.readme.android.di.annotations.NaverBookSearchServer
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    const val NAVER_BOOK_SEARCH_BASE_URL = "https://openapi.naver.com/v1/search/"

    @Provides
    @Singleton
    fun providesHttpLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }


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
    fun providesOkHttpClient(
        @NaverBookSearchServer interceptor: Interceptor,
        httpLoggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient =
        OkHttpClient.Builder()
            .connectTimeout(15, TimeUnit.SECONDS)
            .writeTimeout(20, TimeUnit.SECONDS)
            .readTimeout(15, TimeUnit.SECONDS)
            .addInterceptor(interceptor)
            .addInterceptor(httpLoggingInterceptor)
            .build()

    @Provides
    @Singleton
    @NaverBookSearchServer
    fun providesRetrofit(@NaverBookSearchServer okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(NAVER_BOOK_SEARCH_BASE_URL)
            .client(okHttpClient)
            .addCallAdapterFactory(CustomCallAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
}
