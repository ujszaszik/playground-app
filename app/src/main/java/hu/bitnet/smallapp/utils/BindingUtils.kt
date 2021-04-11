@file:Suppress("unused")

package hu.bitnet.smallapp.utils

import android.app.Activity
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.net.Uri
import android.view.View
import android.view.ViewTreeObserver
import android.widget.ImageView
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.databinding.InverseBindingListener
import androidx.databinding.adapters.ListenerUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.BitmapImageViewTarget
import com.bumptech.glide.request.target.Target
import hu.bitnet.smallapp.R
import hu.bitnet.smallapp.entity.BaseEntity
import hu.bitnet.smallapp.extensions.imageDrawable
import hu.bitnet.smallapp.ui.launch.CardListAdapter
import hu.bitnet.smallapp.ui.launch.CardListOnClickListener

@BindingAdapter(value = ["enabled"])
fun View.enabled(enabled: Boolean) {
    isEnabled = enabled
}

@BindingAdapter(value = ["visible"])
fun View.visible(visible: Boolean) {
    visibility = if (visible) View.VISIBLE else View.INVISIBLE
}

@BindingAdapter(value = ["visibleOrGone"])
fun View.visibleOrGone(visible: Boolean) {
    visibility = if (visible) View.VISIBLE else View.GONE
}

@InverseBindingAdapter(attribute = "visibility")
fun View.visibilityInverse(): Int {
    return visibility
}

@BindingAdapter(value = ["visibilityAttrChanged"])
fun visibilityAttrChanged(view: View, listener: InverseBindingListener) {
    val newValue = ViewTreeObserver.OnGlobalLayoutListener {
        val visibility = view.visibility
        val prevVisibility = view.getTag(R.id.visibility) ?: visibility
        view.setTag(R.id.visibility, visibility)
        if (prevVisibility != visibility) {
            listener.onChange()
        }
    }
    ListenerUtil.trackListener(view, newValue, R.id.visibility_listener)
        ?.let { view.viewTreeObserver.removeOnGlobalLayoutListener(it) }
    view.viewTreeObserver.addOnGlobalLayoutListener(newValue)
}

@BindingAdapter(
    value = ["imageAssetName", "circular", "radius", "error", "placeHolder", "onSuccess"],
    requireAll = false
)
fun ImageView.loadImageAsset(
    assetName: String?,
    circular: Boolean = false,
    radius: Float = 0f,
    error: Int = 0,
    placeHolder: Drawable? = null,
    onSuccess: (() -> Unit)? = null
) {
    if (assetName.isNullOrEmpty()) {
        imageDrawable = placeHolder
        return
    }

    if ((this.context as? Activity?)?.isDestroyed == true) {
        return
    }

    val requestOptions = RequestOptions()
        .placeholder(placeHolder)
        .error(error)

    Glide.with(context)
        .asBitmap()
        .apply(requestOptions)
        .load(Uri.parse("file:///android_asset/images/${assetName}"))
        .override(Target.SIZE_ORIGINAL)
        .into(object : BitmapImageViewTarget(this) {
            override fun setResource(bitmap: Bitmap?) {
                setImageBitmap(bitmap, circular, radius)
                onSuccess?.invoke()
            }
        })
}

@BindingAdapter(value = ["imageBitmap", "circular", "radius"])
fun ImageView.setImageBitmap(bitmap: Bitmap?, circular: Boolean, radius: Float) {
    if (bitmap != null) {
        val circularBitmapDrawable = RoundedBitmapDrawableFactory.create(resources, bitmap)
        if (circular) {
            circularBitmapDrawable.isCircular = true
        } else {
            circularBitmapDrawable.cornerRadius = radius
        }
        this.setImageDrawable(circularBitmapDrawable)
    }
}

@BindingAdapter(value = ["nearbyPlaces", "listener"], requireAll = true)
fun RecyclerView.setNearbyPlaces(
    nearbyPlaces: List<BaseEntity>,
    listener: CardListOnClickListener
) {
    this.adapter = CardListAdapter(nearbyPlaces, listener)
}