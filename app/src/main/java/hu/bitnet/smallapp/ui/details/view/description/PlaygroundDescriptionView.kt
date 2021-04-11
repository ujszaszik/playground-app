package hu.bitnet.smallapp.ui.details.view.description

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.databinding.BindingAdapter
import hu.bitnet.smallapp.databinding.LayoutPlaygroundDescriptionBinding
import hu.bitnet.smallapp.entity.PlaygroundEntity

class PlaygroundDescriptionView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private val binding: LayoutPlaygroundDescriptionBinding =
        LayoutPlaygroundDescriptionBinding.inflate(LayoutInflater.from(context), this, true)

    fun bindEntity(entity: PlaygroundEntity) {
        binding.entity = entity
    }
}

@BindingAdapter("app:descriptionPlayground")
fun PlaygroundDescriptionView.playground(entity: PlaygroundEntity?) {
    entity?.let { bindEntity(it) }
}