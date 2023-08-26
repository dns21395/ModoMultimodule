package uk.nightlines.feature.settings.main_impl.ui

import androidx.compose.ui.graphics.Color
import kotlin.random.Random

internal data class SettingsViewState(
    val backgroundColor: Color = Color(Random.nextInt()),
)