package uk.nightlines.feature.settings.two_impl.ui

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import uk.nightlines.core.navigation.setstack.NavigationReplace
import uk.nightlines.core.navigation.setstack.NavigationTypeSetStack
import uk.nightlines.feature.settings.common.SettingsNavigationQualifier
import uk.nightlines.feature.settings.common.SettingsScreens
import javax.inject.Inject

internal class SettingsTwoViewModel @Inject constructor(
    @SettingsNavigationQualifier private val settingsNavigation: NavigationTypeSetStack,
    private val settingsScreens: SettingsScreens,
) : ViewModel() {

    private val mutableState = MutableStateFlow(SettingsTwoViewState())
    val state: StateFlow<SettingsTwoViewState> = mutableState
    suspend fun openSettingsOneScreenButtonClicked() {
        settingsNavigation.navigate(NavigationReplace(settingsScreens.oneScreen()))
    }
}