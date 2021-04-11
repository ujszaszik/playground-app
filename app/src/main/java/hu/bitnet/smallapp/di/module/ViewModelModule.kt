package hu.bitnet.smallapp.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import hu.bitnet.smallapp.di.ViewModelFactory
import hu.bitnet.smallapp.di.annotation.ViewModelKey
import hu.bitnet.smallapp.ui.details.DetailsViewModel
import hu.bitnet.smallapp.ui.launch.LaunchViewModel

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(LaunchViewModel::class)
    abstract fun bindLaunchViewModel(launchViewModel: LaunchViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DetailsViewModel::class)
    abstract fun bindDetailsViewModel(detailsViewModel: DetailsViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

}