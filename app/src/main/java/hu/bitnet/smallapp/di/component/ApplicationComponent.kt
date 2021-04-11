package hu.bitnet.smallapp.di.component

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.content.res.Resources
import com.patloew.rxlocation.RxLocation
import dagger.Component
import dagger.android.AndroidInjectionModule
import hu.bitnet.smallapp.base.BaseActivity
import hu.bitnet.smallapp.base.BaseFragment
import hu.bitnet.smallapp.database.ICoreDatabase
import hu.bitnet.smallapp.di.module.*
import hu.bitnet.smallapp.manager.locale.ILocaleManager
import hu.bitnet.smallapp.manager.settings.ISettingsManager
import hu.bitnet.smallapp.repository.IPlacesRepository
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        AndroidModule::class,
        ApplicationModule::class,
        BitNetModule::class,
        EncryptionModule::class,
        LocationModule::class
    ]
)
interface ApplicationComponent {

    fun application(): Application

    fun context(): Context

    fun localeManager(): ILocaleManager

    fun settingsManager(): ISettingsManager

    fun packageManager(): PackageManager

    fun resources(): Resources

    fun rxLocation(): RxLocation

    fun sharedPreferences(): SharedPreferences

    fun coreDatabase(): ICoreDatabase

    fun placesRepository(): IPlacesRepository

    fun inject(baseActivity: BaseActivity)

    fun inject(baseFragment: BaseFragment)
}