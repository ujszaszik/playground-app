package hu.bitnet.smallapp.ui.details.view.data.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import hu.bitnet.smallapp.databinding.LayoutPlaygroundDataTextBinding
import hu.bitnet.smallapp.entity.BaseProperty
import hu.bitnet.smallapp.entity.HeaderedProperty

class PlaygroundDataTextViewHolder private constructor(view: View) :
    PlaygroundDataViewHolder(view) {

    companion object {

        const val VIEW_TYPE = 0

        private lateinit var binding: LayoutPlaygroundDataTextBinding

        fun from(parent: ViewGroup): PlaygroundDataTextViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            binding = LayoutPlaygroundDataTextBinding.inflate(inflater, parent, false)
            return PlaygroundDataTextViewHolder(binding.root)
        }
    }

    override fun bindProperty(property: BaseProperty) {
        binding.property = property as HeaderedProperty
        binding.executePendingBindings()
    }
}