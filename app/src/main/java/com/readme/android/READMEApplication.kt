package com.readme.android

import android.app.Application
import com.kakao.sdk.common.KakaoSdk
import com.readme.android.BuildConfig.KAKAO_NATIVE_APP_KEY
import com.readme.android.core_ui.util.READMEDebugTree
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class READMEApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(READMEDebugTree())
        }
        KakaoSdk.init(this, KAKAO_NATIVE_APP_KEY)
    }
}
