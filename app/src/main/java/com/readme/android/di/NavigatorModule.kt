package com.readme.android.di

import com.readme.android.navigator.MainNavigator
import com.readme.android.navigator.MainNavigatorImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
internal abstract class NavigatorModule {
    @Binds
    abstract fun provideMainNavigator(
        navigator: MainNavigatorImpl
    ): MainNavigator
}
