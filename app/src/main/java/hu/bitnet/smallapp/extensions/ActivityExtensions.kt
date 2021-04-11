package hu.bitnet.smallapp.extensions

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.view.View
import androidx.core.view.ViewCompat
import hu.bitnet.smallapp.R
import hu.bitnet.smallapp.entity.BaseEntity
import hu.bitnet.smallapp.ui.details.share.ShareData

fun <T : View?> Activity.transitionNameFor(view: T): String? {
    return view?.let { ViewCompat.getTransitionName(it) }
}

fun Activity.navigateBackWhenClickOn(view: View) {
    view.setOnClickListener { onBackPressed() }
}

fun Activity.shareData(text: String) {
    Intent(Intent.ACTION_SEND).run {
        type = textType
        putExtra(Intent.EXTRA_TEXT, text)
        startActivity(Intent.createChooser(this, getString(R.string.details_share_label)))
    }
}

fun Activity.buildShareText(shareData: ShareData): String {
    return getString(R.string.share_app_description, shareData.name, shareData.address)
}

@SuppressLint("QueryPermissionsNeeded")
fun Activity.showPlaygroundLocation(entity: BaseEntity) {
    val geoUri = geoUriFromEntity(entity.latitude, entity.longitude, entity.name!!)
    val mapIntent = Intent(Intent.ACTION_VIEW, geoUri)
    mapIntent.setPackage(mapsPackageName)
    mapIntent.resolveActivity(packageManager)?.let {
        startActivity(mapIntent)
    }
}

val Activity.mapsPackageName: String
    get() = "com.google.android.apps.maps"

val Activity.textType: String
    get() = "text/html"