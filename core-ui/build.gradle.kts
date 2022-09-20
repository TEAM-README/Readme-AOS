plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
}

android {
    buildFeatures {
        dataBinding = true
    }
    namespace = "com.readme.android.core_ui"
}

dependencies {
    implementation(project(":shared"))
    implementation(project(":navigator"))

    implementation(ThirdPartyDependencies.timber)

    // Android Core
    implementation(AndroidXDependencies.coreKtx)
    implementation(AndroidXDependencies.appCompat)
    implementation(AndroidXDependencies.constraintLayout)
    implementation(AndroidXDependencies.legacy)
    implementation(AndroidXDependencies.coroutines)
    implementation(AndroidXDependencies.fragment)
    coreLibraryDesugaring(AndroidXDependencies.desugarLibrary)

    // Dagger-Hilt
    implementation(AndroidXDependencies.hilt)
    kapt(KaptDependencies.hiltCompiler)

    // Material Design
    implementation(MaterialDesignDependencies.materialDesign)

    // ImageLoading Library
    implementation(ThirdPartyDependencies.coil)
}
