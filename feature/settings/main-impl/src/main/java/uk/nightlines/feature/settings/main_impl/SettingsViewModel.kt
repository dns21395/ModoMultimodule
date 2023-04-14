package uk.nightlines.feature.settings.main_impl

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.terrakok.modo.Screen
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import uk.nightlines.core.common.RootScreensInteractor
import uk.nightlines.core.common.RootScreensCounterInteractor
import uk.nightlines.core.navigation.*
import uk.nightlines.core.navigation.type.NavigationTypeCommand
import uk.nightlines.core.navigation.type.NavigationTypeSetStack
import uk.nightlines.feature.settings.common.SettingsNavigationQualifier
import uk.nightlines.feature.settings.common.SettingsScreens
import javax.inject.Inject

internal class SettingsViewModel @Inject constructor(
    @RootNavigationQualifier private val rootNavigation: NavigationTypeCommand,
    @SettingsNavigationQualifier private val settingsNavigation: NavigationTypeSetStack,
    private val settingsScreens: SettingsScreens,
    private val screenCounterInteractor: RootScreensCounterInteractor,
    private val rootScreens: RootScreensInteractor,
    ) : ViewModel() {

    val navigationCommands: Flow<List<Screen>> = settingsNavigation.screensStackFlow

    private val _state = MutableStateFlow(SettingsStateViewState())
    val state: StateFlow<SettingsStateViewState> = _state

    init {
        viewModelScope.launch {
            settingsNavigation.navigate(NavigationForward(settingsScreens.oneScreen()))
        }
    }

    suspend fun onShowOptionsButtonClicked() {
        _state.emit(state.value.copy(isOptionsVisible = !state.value.isOptionsVisible))
    }

    suspend fun onBackButtonClicked() {
        settingsNavigation.navigate(NavigationBack)
    }

    suspend fun onForwardButtonClicked() {
        val counter = screenCounterInteractor.getCommandScreenCount()
        rootNavigation.navigate(NavigationForward(rootScreens.setStackScreen(counter)))
    }

    suspend fun onReplaceButtonClicked() {
        val counter = screenCounterInteractor.getCommandScreenCount()
        rootNavigation.navigate(NavigationReplace(rootScreens.setStackScreen(counter)))
    }

    suspend fun onRemoveEditTextPositionChanged(text: String) {
        _state.emit(_state.value.copy(positionEditText = text))
    }

    suspend fun onRemoveScreensButtonClicked() {
        val list = state.value.positionEditText.split(',').map { it.toInt() }
        settingsNavigation.navigate(NavigationRemoveScreen(list))

        _state.emit(_state.value.copy(positionEditText = ""))
    }

    suspend fun onBackToSecondScreenClicked() {
        // TODO
    }
}