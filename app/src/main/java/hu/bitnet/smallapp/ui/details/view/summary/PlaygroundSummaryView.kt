package hu.bitnet.smallapp.ui.details.view.summary

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingAdapter
import hu.bitnet.smallapp.databinding.LayoutPlaygroundSummaryBinding
import hu.bitnet.smallapp.entity.PlaygroundEntity
import hu.bitnet.smallapp.utils.DistanceTextFormatter

class PlaygroundSummaryView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val binding: LayoutPlaygroundSummaryBinding =
        LayoutPlaygroundSummaryBinding.inflate(LayoutInflater.from(context), this, true)

    fun bindEntity(entity: PlaygroundEntity) {
        binding.entity = entity
    }

    fun bindDistance(distance: Double?) {
        distance?.let { binding.distance = distance }
    }

}

@BindingAdapter("app:summaryPlayground")
fun PlaygroundSummaryView.playground(entity: PlaygroundEntity?) {
    entity?.let { bindEntity(it) }
}

@BindingAdapter("app:playgroundDistance")
fun PlaygroundSummaryView.distance(distance: Double?) {
    distance?.let { bindDistance(it) }
}

@BindingAdapter("app:distanceText")
fun TextView.distanceText(distance: Double?) {
    DistanceTextFormatter.format(distance)?.let { text = it }
}