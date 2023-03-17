package uk.nightlines.feature.settings.one_impl.ui

import androidx.compose.ui.graphics.Color
import uk.nightlines.core.common.emojis
import kotlin.random.Random

data class SettingsOneViewState(
    val color: Color = Color(Random.nextInt()),
    val emoji: String = emojis[Random.nextInt(emojis.size - 1)]
)
