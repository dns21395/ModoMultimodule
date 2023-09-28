package uk.nightlines.core.di

import androidx.compose.runtime.compositionLocalOf

val LocalCoreProvider = compositionLocalOf<CoreProvider> {
    error("CoreProvider not found")
}