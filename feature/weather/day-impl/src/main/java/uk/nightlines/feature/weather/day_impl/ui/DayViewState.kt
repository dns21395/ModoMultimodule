package uk.nightlines.feature.weather.day_impl.ui

import androidx.compose.ui.graphics.Color
import kotlin.random.Random

data class DayViewState(
    val editText: String = "",
    val color: Color = Color(Random.nextInt())
)