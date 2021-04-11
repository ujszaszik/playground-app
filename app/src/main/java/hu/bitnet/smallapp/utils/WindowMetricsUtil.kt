package hu.bitnet.smallapp.utils

import android.app.Activity
import android.util.DisplayMetrics

object WindowMetricsUtil {

    @Suppress("DEPRECATION")
    fun getCurrentHeight(activity: Activity): Int {
        val windowManager = activity.windowManager
        return if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.R) {
            windowManager.currentWindowMetrics.bounds.height()
        } else {
            val displayMetrics = DisplayMetrics()
            windowManager.defaultDisplay.getMetrics(displayMetrics)
            displayMetrics.heightPixels
        }
    }
}