package hu.bitnet.smallapp.di.module

import android.content.Context
import com.patloew.rxlocation.RxLocation
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object LocationModule {

    @Singleton
    @Provides
    fun provideRxLocation(context: Context): RxLocation {
        return RxLocation(context)
    }
}