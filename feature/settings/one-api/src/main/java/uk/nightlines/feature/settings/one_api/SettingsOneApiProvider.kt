package uk.nightlines.feature.settings.one_api

import uk.nightlines.feature.settings.common.SetStackDependencies

interface SettingsOneApiProvider : SetStackDependencies {

    fun provideSettingsOneApi(): SettingsOneApi
}