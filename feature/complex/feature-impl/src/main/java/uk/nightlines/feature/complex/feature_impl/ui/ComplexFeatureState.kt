package uk.nightlines.feature.complex.feature_impl.ui

import androidx.compose.ui.graphics.Color
import uk.nightlines.core.common.emojis
import kotlin.random.Random

data class ComplexFeatureState(
    val color: Color = Color(Random.nextInt()),
    val emoji: String = emojis[Random.nextInt(emojis.size - 1)],
    val editText: String = ""
)