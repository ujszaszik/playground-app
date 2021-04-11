package hu.bitnet.smallapp.manager.locale

import android.content.Context
import java.util.*
import javax.inject.Inject

class LocaleManager

    @Inject
    constructor(private val context: Context): ILocaleManager {

    override val locale: Locale = Locale.getDefault()

}