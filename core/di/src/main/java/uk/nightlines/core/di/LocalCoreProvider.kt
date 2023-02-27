package uk.nightlines.core.di

import androidx.compose.runtime.compositionLocalOf

val LocalCoreProvider = compositionLocalOf<CoreProvider> {
    error("SettingsNavigation not found")
}