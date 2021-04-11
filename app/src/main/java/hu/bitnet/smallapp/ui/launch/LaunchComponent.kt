package hu.bitnet.smallapp.ui.launch

import android.content.Context
import android.content.res.Resources
import dagger.Component
import hu.bitnet.smallapp.di.annotation.PluginScope
import hu.bitnet.smallapp.di.component.ApplicationComponent
import hu.bitnet.smallapp.di.module.ViewModelModule

@PluginScope
@Component(dependencies = [ApplicationComponent::class], modules = [ViewModelModule::class])
interface LaunchComponent {

    fun inject(activity: LaunchActivity)

    fun context(): Context

    fun resources(): Resources

}