package uk.nightlines.feature.settings.two_impl.ui

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import uk.nightlines.core.navigation.NavigationForward
import uk.nightlines.core.navigation.NavigationReplace
import uk.nightlines.core.navigation.setstack.NavigationTypeSetStack
import uk.nightlines.feature.settings.common.SetStackNavigationQualifier
import uk.nightlines.feature.settings.one_api.OneScreenApi
import javax.inject.Inject

internal class SettingsTwoViewModel @Inject constructor(
    @SetStackNavigationQualifier private val settingsNavigation: NavigationTypeSetStack,
    private val oneScreenApi: OneScreenApi,
) : ViewModel() {

    private val mutableState = MutableStateFlow(SettingsTwoViewState())
    val state: StateFlow<SettingsTwoViewState> = mutableState

    suspend fun onForwardClicked() {
        settingsNavigation.navigate(NavigationForward(oneScreenApi.getScreen()))

    }

    suspend fun onReplaceClicked() {
        settingsNavigation.navigate(NavigationReplace(oneScreenApi.getScreen()))

    }
}