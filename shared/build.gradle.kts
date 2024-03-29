plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
}

android {
    buildFeatures {
        dataBinding = true
    }
    namespace = "com.readme.android.shared"
}

dependencies {
    implementation(project(":domain"))
    implementation(project(":data"))

    // Android Core
    implementation(AndroidXDependencies.coreKtx)
    implementation(AndroidXDependencies.appCompat)
    implementation(AndroidXDependencies.constraintLayout)
    implementation(AndroidXDependencies.legacy)
    implementation(AndroidXDependencies.coroutines)
    implementation(AndroidXDependencies.splashScreen)

    // Kotlin
    implementation(KotlinDependencies.kotlin)

    implementation(AndroidXDependencies.coreKtx)

    // Material Design
    implementation(MaterialDesignDependencies.materialDesign)
}
