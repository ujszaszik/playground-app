package hu.bitnet.smallapp.ui.details.view.data.adapter

import androidx.recyclerview.widget.DiffUtil
import hu.bitnet.smallapp.entity.BaseProperty

class PlaygroundDataItemCallback : DiffUtil.ItemCallback<BaseProperty>() {

    override fun areItemsTheSame(oldItem: BaseProperty, newItem: BaseProperty): Boolean {
        return oldItem.title == newItem.title
    }

    override fun areContentsTheSame(oldItem: BaseProperty, newItem: BaseProperty): Boolean {
        return oldItem.title == newItem.title
    }
}