package hu.bitnet.smallapp.di

import hu.bitnet.smallapp.base.BaseActivity
import hu.bitnet.smallapp.base.IApplication
import hu.bitnet.smallapp.di.component.ApplicationComponent

object DI {

    private lateinit var mApplicationComponent: ApplicationComponent

    val appComponent: ApplicationComponent
        get() = mApplicationComponent

    val activity: BaseActivity?
        get() = (mApplicationComponent.application() as IApplication).getLiveActivity()

    fun init(applicationComponent: ApplicationComponent) {
        mApplicationComponent = applicationComponent
    }

    fun inject(activity: BaseActivity) {
        (mApplicationComponent.application() as IApplication).setLiveActivity(activity)
    }

}