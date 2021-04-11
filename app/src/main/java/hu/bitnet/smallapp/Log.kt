package hu.bitnet.smallapp

import android.util.Log

@Suppress("unused")
object Log {

    private val isEnabled = BuildConfig.DEBUG

    fun v(message: String) {
        if(isEnabled) {
            Log.v(TAG, message)
        }
    }

    fun v(message: String, exception: Throwable) {
        if(isEnabled) {
            Log.v(TAG, message, exception)
        }
    }

    fun d(message: String) {
        if(isEnabled) {
            Log.d(TAG, message)
        }
    }

    fun d(message: String, exception: Throwable) {
        if(isEnabled) {
            Log.d(TAG, message, exception)
        }
    }

    fun i(message: String) {
        if(isEnabled) {
            Log.i(TAG, message)
        }
    }

    fun i(message: String, exception: Throwable) {
        if(isEnabled) {
            Log.i(TAG, message, exception)
        }
    }

    fun w(message: String) {
        Log.w(TAG, message)
    }

    fun w(message: String, exception: Throwable) {
        Log.w(TAG, message, exception)
    }

    fun e(message: String) {
        Log.e(TAG, message)
    }

    fun e(message: String, exception: Throwable) {
        Log.e(TAG, message, exception)
    }

    fun wtf(message: String) {
        Log.wtf(TAG, message)
    }

    fun wtf(message: String, exception: Throwable) {
        Log.wtf(TAG, message, exception)
    }
}

private const val TAG: String = "PorontyPont"