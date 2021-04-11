package hu.bitnet.smallapp.di.module

import android.app.Application
import android.content.Context
import android.content.pm.PackageManager
import android.content.res.Resources
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
object AndroidModule {

    @Provides
    @Singleton
    fun provideApplicationContext(application: Application): Context {
        return application.applicationContext
    }

    @Provides
    @Singleton
    fun provideResources(context: Context): Resources {
        return context.resources
    }

    @Provides
    @Singleton
    fun providePackageManager(context: Context): PackageManager {
        return context.packageManager
    }
}