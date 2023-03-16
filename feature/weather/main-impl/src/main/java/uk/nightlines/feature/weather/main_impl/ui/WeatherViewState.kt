package uk.nightlines.feature.weather.main_impl.ui

import androidx.compose.ui.graphics.Color
import kotlin.random.Random

internal data class WeatherViewState(
    val backgroundColor: Color = Color(Random.nextInt())
)
