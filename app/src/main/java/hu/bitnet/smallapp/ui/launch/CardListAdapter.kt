package hu.bitnet.smallapp.ui.launch

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import hu.bitnet.smallapp.databinding.ListItemCardBinding
import hu.bitnet.smallapp.entity.BaseEntity

class CardListAdapter
constructor(
    private val entities: List<BaseEntity>,
    private val onClickListener: CardListOnClickListener
) : RecyclerView.Adapter<CardListViewHolder>() {

    override fun getItemCount(): Int = entities.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardListViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ListItemCardBinding = ListItemCardBinding.inflate(
            layoutInflater,
            parent,
            false
        )
        return CardListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CardListViewHolder, position: Int) {
        val entity = entities[position]
        holder.bind(entity)
        initListener(holder, entity)
    }

    private fun initListener(holder: CardListViewHolder, entity: BaseEntity) {
        with(holder) {
            itemView.setOnClickListener {
                onClickListener.onImageClicked(entity, cardImage)
            }
        }
    }
}