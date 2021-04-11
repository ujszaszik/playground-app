package hu.bitnet.smallapp.manager.settings

import android.content.SharedPreferences
import javax.inject.Inject

class SettingsManager

    @Inject
    constructor(private val sharedPreferences: SharedPreferences): ISettingsManager {

    override fun getSharedPreferencesBooleanValue(key: String, defaultValue: Boolean): Boolean {
        return sharedPreferences.getBoolean(key, defaultValue)
    }

    override fun setSharedPreferencesBooleanValue(key: String, value: Boolean) {
        sharedPreferences.edit().putBoolean(key, value).apply()
    }

}