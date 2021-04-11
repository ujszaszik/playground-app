package hu.bitnet.smallapp.ui.details.view.data.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import hu.bitnet.smallapp.entity.BaseProperty
import hu.bitnet.smallapp.entity.BooleanProperty
import hu.bitnet.smallapp.entity.HeaderedProperty

class PlaygroundDataAdapter :
    ListAdapter<BaseProperty, PlaygroundDataViewHolder>(PlaygroundDataItemCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaygroundDataViewHolder {
        return when (viewType) {
            PlaygroundDataTextViewHolder.VIEW_TYPE -> PlaygroundDataTextViewHolder.from(parent)
            PlaygroundDataCheckBoxViewHolder.VIEW_TYPE -> PlaygroundDataCheckBoxViewHolder.from(
                parent
            )
            else -> throw UnsupportedOperationException()
        }
    }

    override fun onBindViewHolder(holder: PlaygroundDataViewHolder, position: Int) {
        holder.bindProperty(getItem(position))
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is HeaderedProperty -> PlaygroundDataTextViewHolder.VIEW_TYPE
            is BooleanProperty -> PlaygroundDataCheckBoxViewHolder.VIEW_TYPE
            else -> 0
        }
    }
}