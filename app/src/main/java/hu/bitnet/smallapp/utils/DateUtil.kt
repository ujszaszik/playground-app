package hu.bitnet.smallapp.utils

import java.util.*

object DateUtil {

    private const val HUNGARIAN_TIME_ZONE = "Europe/Budapest"

    fun setHungarianTimeZone() {
        TimeZone.setDefault(TimeZone.getTimeZone(HUNGARIAN_TIME_ZONE))
    }

}