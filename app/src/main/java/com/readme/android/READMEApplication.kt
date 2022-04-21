package com.readme.android

import android.app.Application
import com.readme.android.core.util.READMEDebugTree
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class READMEApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(READMEDebugTree())
        }
    }
}
