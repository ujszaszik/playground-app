package hu.bitnet.smallapp.ui.details.view.data.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import hu.bitnet.smallapp.databinding.LayoutPlaygroundDataCheckboxBinding
import hu.bitnet.smallapp.entity.BaseProperty
import hu.bitnet.smallapp.entity.BooleanProperty

class PlaygroundDataCheckBoxViewHolder private constructor(view: View) :
    PlaygroundDataViewHolder(view) {

    companion object {

        const val VIEW_TYPE = 1

        private lateinit var binding: LayoutPlaygroundDataCheckboxBinding

        fun from(parent: ViewGroup): PlaygroundDataCheckBoxViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            binding = LayoutPlaygroundDataCheckboxBinding.inflate(inflater, parent, false)
            return PlaygroundDataCheckBoxViewHolder(binding.root)
        }
    }

    override fun bindProperty(property: BaseProperty) {
        binding.property = property as BooleanProperty
        binding.executePendingBindings()
    }
}