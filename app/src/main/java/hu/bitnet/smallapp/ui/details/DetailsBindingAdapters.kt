package hu.bitnet.smallapp.ui.details

import android.widget.TextView
import androidx.databinding.BindingAdapter

@BindingAdapter("app:detailsUiModel")
fun TextView.detailsUiModel(uiModel: DetailsUIModel) {
    uiModel.currentPlayground?.let {
        text = it.name
    }
}