package hu.bitnet.smallapp.manager.settings

interface ISettingsManager {

    fun getSharedPreferencesBooleanValue(key: String, defaultValue: Boolean): Boolean

    fun setSharedPreferencesBooleanValue(key: String, value: Boolean)

}