package uk.nightlines.core.common.state

import androidx.compose.ui.graphics.Color
import uk.nightlines.core.common.emojis
import kotlin.random.Random

data class SimpleEditTextState(
    val color: Color = Color(Random.nextInt()),
    val emoji: String = emojis[Random.nextInt(emojis.size - 1)],
    val editText: String = ""
)