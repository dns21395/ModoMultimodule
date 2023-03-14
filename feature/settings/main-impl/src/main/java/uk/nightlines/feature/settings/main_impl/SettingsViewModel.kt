package uk.nightlines.feature.settings.main_impl

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.SharedFlow
import uk.nightlines.core.navigation.Navigation
import uk.nightlines.core.navigation.NavigationCommand
import uk.nightlines.core.navigation.RootNavigationQualifier
import uk.nightlines.feature.settings.common.SettingsNavigationQualifier
import javax.inject.Inject

internal class SettingsViewModel @Inject constructor(
    @RootNavigationQualifier private val rootNavigation: Navigation,
    @uk.nightlines.feature.settings.common.SettingsNavigationQualifier private val settingsNavigation: Navigation,
) : ViewModel() {

    val navigationCommands: SharedFlow<NavigationCommand> = settingsNavigation.commandsFlow

}