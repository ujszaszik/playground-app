package hu.bitnet.smallapp.di.module

import dagger.Binds
import dagger.Module
import hu.bitnet.smallapp.database.CoreDatabase
import hu.bitnet.smallapp.database.ICoreDatabase
import hu.bitnet.smallapp.manager.locale.ILocaleManager
import hu.bitnet.smallapp.manager.locale.LocaleManager
import hu.bitnet.smallapp.manager.settings.ISettingsManager
import hu.bitnet.smallapp.manager.settings.SettingsManager
import hu.bitnet.smallapp.repository.IPlacesRepository
import hu.bitnet.smallapp.repository.PlacesRepository
import javax.inject.Singleton

@Module
interface BitNetModule {

    @Binds
    @Singleton
    fun coreDatabase(coreDatabase: CoreDatabase): ICoreDatabase

    @Binds
    @Singleton
    fun placesRepository(placesRepository: PlacesRepository): IPlacesRepository

    @Singleton
    @Binds
    fun localeManager(localeManager: LocaleManager): ILocaleManager

    @Singleton
    @Binds
    fun settingsManager(settingsManager: SettingsManager): ISettingsManager

}