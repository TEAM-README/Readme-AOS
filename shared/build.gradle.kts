plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

dependencies {
    // Android Core
    implementation(AndroidXDependencies.coreKtx)
    implementation(AndroidXDependencies.appCompat)
    implementation(AndroidXDependencies.constraintLayout)
    implementation(AndroidXDependencies.legacy)
    implementation(AndroidXDependencies.coroutines)

    // Kotlin
    implementation(KotlinDependencies.kotlin)

    implementation(AndroidXDependencies.coreKtx)

    // Material Design
    implementation(MaterialDesignDependencies.materialDesign)
}