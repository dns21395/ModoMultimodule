package uk.nightlines.feature.settings.main_impl.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.terrakok.modo.Screen
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import uk.nightlines.core.common.RootScreensInteractor
import uk.nightlines.core.common.state.ContainerState
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
        rootNavigation.navigate(NavigationForward(rootScreens.settingsScreen()))
    }

    suspend fun onReplaceButtonClicked() {
        rootNavigation.navigate(NavigationReplace(rootScreens.settingsScreen()))
    }

    suspend fun onRemoveFirstAndThirdScreensButtonClicked() {
        settingsNavigation.navigate(NavigationRemoveScreen(listOf(0, 2)))
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
        settingsNavigation.navigate(NavigationForward(rootScreens.settingsScreen()))
    }
}