package uk.nightlines.feature.weather.one_impl.ui

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import uk.nightlines.core.common.state.SimpleEditTextState
import uk.nightlines.core.navigation.NavigationForward
import uk.nightlines.core.navigation.NavigationReplace
import uk.nightlines.core.navigation.RootNavigationQualifier
import uk.nightlines.core.navigation.command.NavigationTypeCommand
import uk.nightlines.feature.weather.common.FeatureNavigationQualifier
import uk.nightlines.feature.weather.two_api.WeekScreenApi
import javax.inject.Inject

internal class DayViewModel @Inject constructor(
    @RootNavigationQualifier private val rootNavigation: NavigationTypeCommand,
    @FeatureNavigationQualifier private val navigation: NavigationTypeCommand,
    private val weekScreenApi: WeekScreenApi
) : ViewModel() {

    private val mutableState = MutableStateFlow(SimpleEditTextState())
    val state: StateFlow<SimpleEditTextState> = mutableState

    suspend fun onReplaceButtonClicked() {
        navigation.navigate(NavigationReplace(weekScreenApi.getWeekScreen()))
    }

    suspend fun onForwardButtonClicked() {
        navigation.navigate(NavigationForward(weekScreenApi.getWeekScreen()))

    }

    suspend fun onOpenDialogButtonClicked() {
        rootNavigation.navigate(NavigationForward(DayDialog()))
    }

    suspend fun onTextChangedAction(text: String) {
        mutableState.value = mutableState.value.copy(editText = text)
    }
}