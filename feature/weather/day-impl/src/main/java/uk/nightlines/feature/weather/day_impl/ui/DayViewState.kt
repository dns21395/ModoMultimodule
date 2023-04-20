package uk.nightlines.feature.weather.day_impl.ui

import androidx.compose.ui.graphics.Color
import uk.nightlines.core.common.emojis
import kotlin.random.Random

data class DayViewState(
    val editText: String = "",
    val backgroundColor: Color = Color(Random.nextInt()),
    val emoji: String = emojis[Random.nextInt(emojis.size - 1)]

)