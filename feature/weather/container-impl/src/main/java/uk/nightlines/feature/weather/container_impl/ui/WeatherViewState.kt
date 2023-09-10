package uk.nightlines.feature.weather.container_impl.ui

import androidx.compose.ui.graphics.Color
import uk.nightlines.core.common.emojis
import kotlin.random.Random

internal data class WeatherViewState(
    val backgroundColor: Color = Color(Random.nextInt()),
    val emoji: String = emojis[Random.nextInt(emojis.size - 1)],
    val positionEditText: String = "",
    val isOptionsVisible: Boolean = false
)
