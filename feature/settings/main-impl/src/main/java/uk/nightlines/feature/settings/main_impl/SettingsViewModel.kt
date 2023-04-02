package uk.nightlines.feature.settings.main_impl

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.terrakok.modo.Screen
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import uk.nightlines.core.navigation.*
import uk.nightlines.feature.settings.common.SettingsNavigationQualifier
import uk.nightlines.feature.settings.common.SettingsScreens
import javax.inject.Inject

internal class SettingsViewModel @Inject constructor(
    @RootNavigationQualifier private val rootNavigation: Navigation,
    @SettingsNavigationQualifier private val settingsNavigation: NavigationStackList,
    private val settingsScreens: SettingsScreens
) : ViewModel() {

    val navigationCommands: Flow<List<Screen>> = settingsNavigation.screensStackFlow

    private val mutableState = MutableStateFlow(SettingsStateViewState())
    val state: StateFlow<SettingsStateViewState> = mutableState

    init {
        viewModelScope.launch {
            settingsNavigation.navigate(NavigationForward(settingsScreens.oneScreen()))
        }
    }

    suspend fun onBackButtonClicked() {
        settingsNavigation.navigate(NavigationBack)
    }
}