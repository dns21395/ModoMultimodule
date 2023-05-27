package uk.nightlines.core.common

import androidx.compose.ui.graphics.Color
import kotlin.random.Random

data class ContainerState(
    val backgroundColor: Color = Color(Random.nextInt()),
    val emoji: String = emojis[Random.nextInt(emojis.size - 1)],
    val positionEditText: String = "",
    val isOptionsVisible: Boolean = false
)