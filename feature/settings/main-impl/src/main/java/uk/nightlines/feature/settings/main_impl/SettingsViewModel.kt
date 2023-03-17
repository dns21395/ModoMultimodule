package uk.nightlines.feature.settings.main_impl

    import androidx.lifecycle.ViewModel
    import androidx.lifecycle.viewModelScope
    import kotlinx.coroutines.flow.Flow
    import kotlinx.coroutines.flow.MutableStateFlow
    import kotlinx.coroutines.flow.StateFlow
    import kotlinx.coroutines.launch
    import uk.nightlines.core.navigation.Navigation
import uk.nightlines.core.navigation.NavigationCommand
    import uk.nightlines.core.navigation.NavigationReplace
    import uk.nightlines.core.navigation.RootNavigationQualifier
import uk.nightlines.feature.settings.common.SettingsNavigationQualifier
    import uk.nightlines.feature.settings.common.SettingsScreens
    import uk.nightlines.feature.settings.one_impl.ui.SettingsOneViewState
    import javax.inject.Inject

internal class SettingsViewModel @Inject constructor(
    @RootNavigationQualifier private val rootNavigation: Navigation,
    @SettingsNavigationQualifier private val settingsNavigation: Navigation,
    private val settingsScreens: SettingsScreens
) : ViewModel() {


    private val mutableState = MutableStateFlow(SettingsStateViewState())
    val state: StateFlow<SettingsStateViewState> = mutableState

    init {
        viewModelScope.launch {
            settingsNavigation.navigate(NavigationReplace(settingsScreens.oneScreen()))
        }
    }

    val navigationCommands: Flow<NavigationCommand> = settingsNavigation.commandsFlow

}