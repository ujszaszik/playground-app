package hu.bitnet.smallapp.ui.launch

import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import hu.bitnet.smallapp.databinding.ListItemCardBinding
import hu.bitnet.smallapp.entity.BaseEntity

class CardListViewHolder(private val binding: ListItemCardBinding) :
    RecyclerView.ViewHolder(binding.root) {

    internal val cardImage: ImageView = binding.cardImage

    fun bind(entity: BaseEntity) {
        binding.item = entity
        setImageTransitionName(entity)
        binding.executePendingBindings()
    }

    private fun setImageTransitionName(entity: BaseEntity) {
        binding.cardImage.transitionName = "${entity.name}"
    }
}