package hu.bitnet.smallapp.base

interface IApplication {

    fun setLiveActivity(activity: BaseActivity)

    fun getLiveActivity(): BaseActivity?

}