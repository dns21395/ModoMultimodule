package uk.nightlines.feature.weather.one_impl.ui

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import uk.nightlines.core.common.state.SimpleEditTextState
import uk.nightlines.core.navigation.NavigationForward
import uk.nightlines.core.navigation.RootNavigationQualifier
import uk.nightlines.core.navigation.command.NavigationTypeCommand
import uk.nightlines.feature.weather.common.FeatureNavigationQualifier
import javax.inject.Inject

internal class DayViewModel @Inject constructor(
    @RootNavigationQualifier private val rootNavigation: NavigationTypeCommand,
    @FeatureNavigationQualifier private val navigation: NavigationTypeCommand,

) : ViewModel() {

    private val mutableState = MutableStateFlow(SimpleEditTextState())
    val state: StateFlow<SimpleEditTextState> = mutableState

    suspend fun onReplaceButtonClicked() {

    }

    suspend fun onForwardButtonClicked() {
    }

    suspend fun onOpenDialogButtonClicked() {
        rootNavigation.navigate(NavigationForward(DayDialog()))
    }

    suspend fun onTextChangedAction(text: String) {
        mutableState.value = mutableState.value.copy(editText = text)
    }
}