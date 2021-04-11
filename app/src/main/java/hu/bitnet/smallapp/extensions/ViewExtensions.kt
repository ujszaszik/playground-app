package hu.bitnet.smallapp.extensions

import android.content.res.ColorStateList
import android.graphics.Paint
import android.graphics.Typeface
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.view.View
import android.view.ViewTreeObserver
import android.widget.CompoundButton
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.Guideline


var View?.backgroundColor: Int
    get() = this?.background.let { bg ->
        when (bg) {
            is ColorDrawable -> bg.color
            else -> 0
        }
    }
    set(value) {
        this?.background = ColorDrawable(value)
    }

var View?.onClickListener: (() -> Unit)?
    get() = null
    set(value) {
        this?.setOnClickListener {
            value?.invoke()
        }
    }

var View?.onClickListener2: View.OnClickListener?
    get() = null
    set(value) {
        this?.setOnClickListener(value)
    }

var CompoundButton?.onCheckedChangeListener: CompoundButton.OnCheckedChangeListener?
    get() = null
    set(value) {
        this?.setOnCheckedChangeListener(value)
    }

var TextView?.textColor: Int
    get() = this?.currentTextColor ?: 0
    set(value) {
        this?.setTextColor(value)
    }

var TextView?.textColorStates: ColorStateList
    get() = ColorStateList.valueOf(this?.currentTextColor ?: 0)
    set(value) {
        this?.setTextColor(value)
    }

var TextView?.customTypeface: Typeface?
    get() = this?.typeface
    set(value) {
        this?.let {
            it.paintFlags = it.paintFlags or Paint.SUBPIXEL_TEXT_FLAG
            it.typeface = value
        }
    }

var TextView?.hintTextColor: Int
    get() = this?.currentHintTextColor ?: 0
    set(value) {
        this?.setHintTextColor(value)
    }

var ImageView?.imageDrawable: Drawable?
    get() = this?.drawable
    set(value) {
        this?.setImageDrawable(value)
    }

fun View.show() {
    visibility = View.VISIBLE
}

fun View.hide() {
    visibility = View.INVISIBLE
}

fun View.gone() {
    visibility = View.GONE
}

fun View.getVisibleHeight(): Int {
    var height = 0
    viewTreeObserver.addOnPreDrawListener(object : ViewTreeObserver.OnPreDrawListener {
        override fun onPreDraw(): Boolean {
            viewTreeObserver.removeOnPreDrawListener(this)
            height = measuredHeight
            return true
        }
    })
    return height
}

fun Guideline.getPercent(): Float {
    val params = layoutParams as ConstraintLayout.LayoutParams
    return params.guidePercent
}