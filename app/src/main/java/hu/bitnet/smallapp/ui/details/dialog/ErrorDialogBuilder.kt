package hu.bitnet.smallapp.ui.details.dialog

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import hu.bitnet.smallapp.R
import kotlin.reflect.KClass

class ErrorDialogBuilder private constructor(private val activity: Activity) {

    private val alertDialogBuilder = AlertDialog.Builder(activity)

    companion object {
        fun onView(activity: Activity): ErrorDialogBuilder {
            return ErrorDialogBuilder(activity)
        }
    }

    fun title(resId: Int): ErrorDialogBuilder {
        alertDialogBuilder.setTitle(activity.getString(resId))
        return this
    }

    fun message(resId: Int): ErrorDialogBuilder {
        alertDialogBuilder.setMessage(activity.getString(resId))
        return this
    }

    fun <T : Activity> onOkGoBackTo(activityClass: KClass<T>): ErrorDialogBuilder {
        alertDialogBuilder.setPositiveButton(activity.getString(R.string.error_details_dialog_ok))
        { _, _ -> navigateBackToActivity(activityClass) }
        return this
    }

    fun show(): AlertDialog = alertDialogBuilder.show()


    private fun <T : Activity> navigateBackToActivity(activityClass: KClass<T>) {
        with(activity) {
            startActivity(Intent(this, activityClass.java))
        }
    }
}