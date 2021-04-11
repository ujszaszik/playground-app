package hu.bitnet.smallapp.ui.details.dialog

import android.app.Activity
import hu.bitnet.smallapp.R
import hu.bitnet.smallapp.ui.launch.LaunchActivity

object EntityNotFoundDialog : ActivityErrorDialog {

    override fun show(activity: Activity) {
        ErrorDialogBuilder.onView(activity)
            .title(R.string.error_details_dialog_title)
            .message(R.string.error_details_dialog_not_found_message)
            .onOkGoBackTo(LaunchActivity::class)
            .show()
    }
}