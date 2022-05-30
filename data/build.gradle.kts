plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
}

dependencies {
    implementation(project(":shared"))
    implementation(project(":core"))
    implementation(project(":domain"))

    // Logger - Timber
    implementation(ThirdPartyDependencies.timber)

    // Kotlin
    implementation(KotlinDependencies.kotlin)
    implementation(KotlinDependencies.kotlinxSerialization)
    implementation(KotlinDependencies.dateTime)

    // Android Core
    implementation(AndroidXDependencies.coreKtx)
    implementation(AndroidXDependencies.coroutines)

    // Dagger-Hilt
    implementation(AndroidXDependencies.hilt)
    kapt(KaptDependencies.hiltCompiler)

    // Jetpack Lifecycle
    implementation(AndroidXDependencies.coroutines)
    implementation(AndroidXDependencies.lifeCycleKtx)
    implementation(AndroidXDependencies.lifecycleJava8)

    // Http Client Library
    implementation(ThirdPartyDependencies.retrofit)
    implementation(platform(ThirdPartyDependencies.okHttpBom))
    implementation(ThirdPartyDependencies.okHttp)
    implementation(ThirdPartyDependencies.okHttpLoggingInterceptor)

    //JsonConverterLibrary
    implementation(ThirdPartyDependencies.gson)
    implementation(ThirdPartyDependencies.gsonConverter)

}
android {
    namespace = "com.readme.android.data"
}
