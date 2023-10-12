package uk.nightlines.core.di

import androidx.compose.runtime.compositionLocalOf
import uk.nightlines.core.di.di.CoreProvider

val LocalCoreProvider = compositionLocalOf<CoreProvider> {
    error("CoreProvider not found")
}