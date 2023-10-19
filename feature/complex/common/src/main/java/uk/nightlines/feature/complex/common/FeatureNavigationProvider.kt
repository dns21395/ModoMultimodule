package uk.nightlines.feature.complex.common

import uk.nightlines.core.navigation.Navigation

interface FeatureNavigationProvider {

    @FeatureNavigationQualifier
    fun complexNavigation(): Navigation
}