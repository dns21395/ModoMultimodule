package uk.nightlines.feature.settings.main_impl.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.terrakok.modo.Screen
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import uk.nightlines.core.common.state.ContainerState
import uk.nightlines.core.common.RootScreensCounterInteractor
import uk.nightlines.core.common.RootScreensInteractor
import uk.nightlines.core.navigation.*
import uk.nightlines.core.navigation.command.NavigationTypeCommand
import uk.nightlines.core.navigation.setstack.NavigationTypeSetStack
import uk.nightlines.feature.settings.common.SetStackNavigationQualifier
import uk.nightlines.feature.settings.one_api.OneScreenApi
import uk.nightlines.feature.settings.two_api.TwoScreenApi
import javax.inject.Inject

internal class SettingsViewModel @Inject constructor(
    @RootNavigationQualifier private val rootNavigation: NavigationTypeCommand,
    @SetStackNavigationQualifier private val settingsNavigation: NavigationTypeSetStack,
    private val oneScreenApi: OneScreenApi,
    private val twoScreenApi: TwoScreenApi,
    private val screenCounterInteractor: RootScreensCounterInteractor,
    private val rootScreens: RootScreensInteractor,
) : ViewModel() {

    val screensStack: Flow<List<Screen>> = settingsNavigation.screensStackFlow

    private val _state = MutableStateFlow(ContainerState())
    val state: StateFlow<ContainerState> = _state

    init {
        viewModelScope.launch {
            settingsNavigation.navigate(NavigationForward(oneScreenApi.getScreen()))
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
        val isSizeMoreThanTwo = settingsNavigation.getState().size > 1

        if (isSizeMoreThanTwo) {
            settingsNavigation.navigate(NavigationBackTo(settingsNavigation.getState()[1]))
        }
    }

    suspend fun onBackToRootClicked() {
        settingsNavigation.navigate(NavigationBackToRoot)
    }

    suspend fun onNewStackButtonClicked() {
        settingsNavigation.navigate(
            NavigationNewStack(
                oneScreenApi.getScreen(),
                listOf(
                    twoScreenApi.getScreen(),
                    oneScreenApi.getScreen(),
                    twoScreenApi.getScreen()
                )
            )
        )
    }

    suspend fun onMultiForwardButtonClicked() {
        settingsNavigation.navigate(
            NavigationForward(
                oneScreenApi.getScreen(),
                listOf(
                    twoScreenApi.getScreen(),
                    oneScreenApi.getScreen(),
                    twoScreenApi.getScreen()
                )
            )
        )
    }

    suspend fun onNewRootButtonClicked() {
        settingsNavigation.navigate(NavigationNewStack(oneScreenApi.getScreen()))
    }

    suspend fun onContainerButtonClicked() {
        val setStackCounter = screenCounterInteractor.getSetStackScreenCount()

        settingsNavigation.navigate(NavigationForward(rootScreens.setStackScreen(setStackCounter)))
    }
}