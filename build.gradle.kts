buildscript {
    repositories {
        google()
        mavenCentral()
        maven { setUrl("https://plugins.gradle.org/m2/") }
    }
    dependencies {
        classpath("com.android.tools.build:gradle:${Versions.gradleVersion}")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlinVersion}")
        classpath("com.google.gms:google-services:${Versions.googleServiceVersion}")
        classpath(ClassPathPlugins.crashlytics)
        classpath(ClassPathPlugins.hilt)
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}

subprojects {
    afterEvaluate {
        project.apply("$rootDir/gradle/common.gradle")
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
