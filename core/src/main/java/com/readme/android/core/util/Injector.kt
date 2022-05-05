package com.readme.android.core.util

import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

sealed interface Injector {
    @EntryPoint
    @InstallIn(ActivityComponent::class)
    interface ResolutionMetricsInjector {
        fun resolutionMetrics(): ResolutionMetrics
    }
}
