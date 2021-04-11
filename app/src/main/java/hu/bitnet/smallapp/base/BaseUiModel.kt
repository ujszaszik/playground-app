package hu.bitnet.smallapp.base

import android.os.Handler
import android.os.Looper
import androidx.databinding.BaseObservable

open class BaseUiModel : BaseObservable() {

    companion object {

        private const val RELOAD_TIMEOUT = 16L

    }

    private val handler = Handler(Looper.myLooper()!!)

    private var reloadInProgress = false

    fun reload() {
        synchronized(RELOAD_TIMEOUT) {
            if (reloadInProgress) return
            reloadInProgress = true
        }

        handler.postDelayed(::doReload, RELOAD_TIMEOUT)
    }

    private fun doReload() {
        notifyChange()
        reloadInProgress = false
    }

}