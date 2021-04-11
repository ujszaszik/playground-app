package hu.bitnet.smallapp.ui.launch

import android.widget.ImageView
import hu.bitnet.smallapp.entity.BaseEntity

interface CardListOnClickListener {
    fun onImageClicked(entity: BaseEntity, imageView: ImageView)
}