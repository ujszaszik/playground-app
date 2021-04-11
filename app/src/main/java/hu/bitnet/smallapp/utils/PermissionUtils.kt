package hu.bitnet.smallapp.utils

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.provider.Settings
import android.view.View
import androidx.annotation.StringRes
import androidx.core.app.ActivityCompat
import com.google.android.material.snackbar.Snackbar
import hu.bitnet.smallapp.R
import hu.bitnet.smallapp.di.DI.appComponent


object PermissionUtils {

    private const val KEY_PREFIX_ASKED_PERMISSION = "ASKED_PERMISSION_"

    @JvmStatic
    fun isLocationPermissionGranted(context: Context): Boolean {
        return ActivityCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
                || ActivityCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_COARSE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
    }

    @JvmStatic
    fun isFirstTimeAskingPermission(permission: String): Boolean {
        return appComponent.settingsManager()
            .getSharedPreferencesBooleanValue(KEY_PREFIX_ASKED_PERMISSION + permission, true)
    }

    @JvmStatic
    fun saveAskedPermissions(vararg permissions: String) {
        val settingsManager = appComponent.settingsManager()
        for (permission in permissions) {
            settingsManager.setSharedPreferencesBooleanValue(
                KEY_PREFIX_ASKED_PERMISSION + permission,
                false
            )
        }
    }

    @StringRes
    fun resourceIdByPermission(permission: String?): Int {
        @StringRes var resourceId: Int = R.string.request_permission
        when (permission) {
            Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION -> resourceId =
                R.string.request_location_permission
        }
        return resourceId
    }

    @JvmStatic
    fun showPermissionSnackbar(permission: String?, activity: Activity?, contentLayout: View?) {
        showPermissionSnackbar(activity!!, contentLayout!!, resourceIdByPermission(permission))
    }

    @JvmStatic
    fun showPermissionSnackbar(activity: Activity, contentLayout: View, @StringRes resId: Int) {
        val permissionSnackbar: Snackbar = Snackbar.make(contentLayout, resId, Snackbar.LENGTH_LONG)
            .setText(R.string.toast_permission_denied_title)
            .setAction(R.string.settings_settings) { _ ->
                val intent: Intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                    .setData(Uri.fromParts("package", activity.packageName, null))
                activity.startActivity(intent)
            }
        permissionSnackbar.show()
    }

}