package uk.nightlines.feature.weather.week_impl.ui

import androidx.compose.ui.graphics.Color
import kotlin.random.Random

data class WeekState(
    val color: Color = Color(Random.nextInt())
)