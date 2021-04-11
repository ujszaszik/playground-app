package hu.bitnet.smallapp.base

import kotlin.properties.ObservableProperty
import kotlin.reflect.KProperty

class UiModelProperty<T>(
    initialValue: T,
    private var changeFirst: Boolean = false,
    private val uiModel: BaseUiModel
) : ObservableProperty<T>(initialValue) {

    override fun afterChange(property: KProperty<*>, oldValue: T, newValue: T) {
        if (oldValue != newValue || changeFirst) {
            changeFirst = false
            uiModel.reload()
        }
    }

}