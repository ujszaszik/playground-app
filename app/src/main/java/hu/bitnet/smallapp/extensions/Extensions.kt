@file:Suppress("unused")

package hu.bitnet.smallapp.extensions

import android.util.TypedValue
import hu.bitnet.smallapp.di.DI
import kotlin.math.roundToInt

val String.Companion.empty: String
    get() = ""

val Char.Companion.comma: Char
    get() = ','

val Int.dp: Int
    get() = (this * DI.appComponent.resources().displayMetrics.density).roundToInt()

val Float.dp: Float
    get() = (this * DI.appComponent.resources().displayMetrics.density)

val Int.sp: Int
    get() = (TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_SP,
        this.toFloat(),
        DI.appComponent.resources().displayMetrics
    )).roundToInt()

val Float.sp: Float
    get() = (TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_SP,
        this,
        DI.appComponent.resources().displayMetrics
    ))


fun <T, R> List<T>.map(transform: (T) -> R): ArrayList<R> {
    return mapTo(ArrayList(this.size), transform)
}