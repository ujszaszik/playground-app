package hu.bitnet.smallapp.ui.details

import dagger.Component
import hu.bitnet.smallapp.di.annotation.PluginScope
import hu.bitnet.smallapp.di.component.ApplicationComponent
import hu.bitnet.smallapp.di.module.ViewModelModule

@PluginScope
@Component(dependencies = [ApplicationComponent::class], modules = [ViewModelModule::class])
interface DetailsComponent {

    fun inject(activity: DetailsActivity)

}