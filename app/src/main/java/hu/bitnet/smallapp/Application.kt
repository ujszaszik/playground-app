package hu.bitnet.smallapp

import hu.bitnet.smallapp.base.BaseActivity
import hu.bitnet.smallapp.base.IApplication
import hu.bitnet.smallapp.di.DI
import hu.bitnet.smallapp.di.component.ApplicationComponent
import hu.bitnet.smallapp.di.component.DaggerApplicationComponent
import hu.bitnet.smallapp.di.module.ApplicationModule

class Application: android.app.Application(), IApplication {

    private var liveActivity: BaseActivity? = null

    override fun onCreate() {
        super.onCreate()
        DI.init(createApplicationComponent())
    }

    private fun createApplicationComponent(): ApplicationComponent {
        return DaggerApplicationComponent.builder()
            .applicationModule(ApplicationModule(this))
            .build()
    }

    override fun setLiveActivity(activity: BaseActivity) {
        liveActivity = activity
        DI.appComponent.inject(activity)
    }

    override fun getLiveActivity(): BaseActivity? {
        return liveActivity
    }

}