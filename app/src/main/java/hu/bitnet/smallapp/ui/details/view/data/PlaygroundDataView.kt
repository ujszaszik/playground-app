package hu.bitnet.smallapp.ui.details.view.data

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingAdapter
import hu.bitnet.smallapp.databinding.LayoutPlaygroundDataBinding
import hu.bitnet.smallapp.entity.BaseProperty
import hu.bitnet.smallapp.ui.details.view.data.adapter.PlaygroundDataAdapter
import hu.bitnet.smallapp.utils.propertyComparator

class PlaygroundDataView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val binding: LayoutPlaygroundDataBinding =
        LayoutPlaygroundDataBinding.inflate(LayoutInflater.from(context), this, true)

    internal fun initProperties(properties: List<BaseProperty>) {
        binding.playgroundDescriptionRecyclerView.adapter = PlaygroundDataAdapter().also {
            it.submitList(properties)
        }
    }
}

@BindingAdapter("app:playgroundProperties")
fun PlaygroundDataView.playgroundProperties(properties: List<BaseProperty>?) {
    properties?.let { initProperties(it.sortedByType()) }
}

private fun List<BaseProperty>.sortedByType(): List<BaseProperty> {
    return this.sortedWith(propertyComparator)
}