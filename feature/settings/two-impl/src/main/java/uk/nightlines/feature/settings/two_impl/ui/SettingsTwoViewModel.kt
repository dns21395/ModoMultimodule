package uk.nightlines.feature.settings.two_impl.ui

import androidx.lifecycle.ViewModel
import uk.nightlines.core.navigation.Navigation
import uk.nightlines.core.navigation.NavigationReplace
import uk.nightlines.feature.settings.common.SettingsNavigationQualifier
import uk.nightlines.feature.settings.common.SettingsScreens
import javax.inject.Inject

internal class SettingsTwoViewModel @Inject constructor(
    @SettingsNavigationQualifier private val settingsNavigation: Navigation,
    private val settingsScreens: SettingsScreens,
) : ViewModel() {

    suspend fun openSettingsOneScreenButtonClicked() {
        settingsNavigation.navigate(NavigationReplace(settingsScreens.oneScreen()))
    }
}