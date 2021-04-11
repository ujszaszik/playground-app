package hu.bitnet.smallapp.ui.details.scroll

import androidx.core.widget.NestedScrollView

class VerticalScrollListener(private val doOnScroll: (Int) -> Unit) :
    NestedScrollView.OnScrollChangeListener {

    override fun onScrollChange(
        v: NestedScrollView?,
        scrollX: Int,
        scrollY: Int,
        oldScrollX: Int,
        oldScrollY: Int
    ) {
        doOnScroll.invoke(scrollY)
    }
}

fun NestedScrollView.addVerticalScrollListener(block: (Int) -> Unit) {
    setOnScrollChangeListener(VerticalScrollListener(block))
}