package hu.bitnet.smallapp.ui.details.view.data.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import hu.bitnet.smallapp.entity.BaseProperty

abstract class PlaygroundDataViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    abstract fun bindProperty(property: BaseProperty)
}