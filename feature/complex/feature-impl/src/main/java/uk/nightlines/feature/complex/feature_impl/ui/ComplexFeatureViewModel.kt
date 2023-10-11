package uk.nightlines.feature.complex.feature_impl.ui

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import uk.nightlines.core.navigation.NavigationForward
import uk.nightlines.core.navigation.NavigationReplace
import uk.nightlines.core.navigation.command.NavigationTypeCommand
import uk.nightlines.feature.weather.common.FeatureNavigationQualifier
import javax.inject.Inject

internal class ComplexFeatureViewModel @Inject constructor(
    @FeatureNavigationQualifier private val navigation: NavigationTypeCommand,

    ) : ViewModel() {

    private val mutableState = MutableStateFlow(ComplexFeatureState())
    val state: StateFlow<ComplexFeatureState> = mutableState

    suspend fun onReplaceButtonClicked() {
        navigation.navigate(NavigationReplace(ComplexScreen()))
    }

    suspend fun onForwardButtonClicked() {
        navigation.navigate(NavigationForward(ComplexScreen()))
    }

    suspend fun onTextChangedAction(text: String) {
        mutableState.value = mutableState.value.copy(editText = text)
    }
}