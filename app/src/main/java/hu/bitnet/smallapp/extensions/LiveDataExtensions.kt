package hu.bitnet.smallapp.extensions

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData

fun <T> LiveData<T>.observeNotNull(activity: AppCompatActivity, block: (T) -> Unit) {
    observe(activity) {
        it?.let { block.invoke(it) }
    }
}

fun LiveData<Boolean>.observeTrue(activity: AppCompatActivity, block: (Boolean) -> Unit) {
    observe(activity) {
        if (it) block.invoke(it)
    }
}