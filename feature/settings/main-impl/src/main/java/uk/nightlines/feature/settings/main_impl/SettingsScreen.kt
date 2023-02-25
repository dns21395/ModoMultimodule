package uk.nightlines.feature.settings.main_impl

import com.github.terrakok.modo.stack.StackNavModel
import com.github.terrakok.modo.stack.StackScreen
import kotlinx.parcelize.Parcelize

@Parcelize
class SettingsStack(
    private val stackNavModel: StackNavModel,
) : StackScreen(stackNavModel) {

    constructor() : this(StackNavModel(emptyList()))
}