package uk.nightlines.feature.settings.main_impl

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.terrakok.modo.NavigationAction
import com.github.terrakok.modo.stack.Back
import com.github.terrakok.modo.stack.Forward
import com.github.terrakok.modo.stack.Replace
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import uk.nightlines.core.navigation.Navigation
import uk.nightlines.core.navigation.NavigationStackList
import uk.nightlines.core.navigation.RootNavigationQualifier
import uk.nightlines.feature.settings.common.SettingsNavigationQualifier
import uk.nightlines.feature.settings.common.SettingsScreens
import javax.inject.Inject

internal class SettingsViewModel @Inject constructor(
    @RootNavigationQualifier private val rootNavigation: Navigation,
    @SettingsNavigationQualifier private val settingsNavigation: NavigationStackList,
    private val settingsScreens: SettingsScreens
) : ViewModel() {

    val navigationCommands: Flow<NavigationAction> = settingsNavigation.screensStackFlow

    private val mutableState = MutableStateFlow(SettingsStateViewState())
    val state: StateFlow<SettingsStateViewState> = mutableState

    init {
        viewModelScope.launch {
            settingsNavigation.navigate(Forward(settingsScreens.oneScreen()))
        }
    }

    suspend fun onBackButtonClicked() {
        settingsNavigation.navigate(Back)
    }
}