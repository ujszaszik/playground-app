package hu.bitnet.smallapp.ui.details.scroll

import android.view.View
import hu.bitnet.smallapp.extensions.greaterOrEqualsThan
import hu.bitnet.smallapp.extensions.one

class AlphaOnScrollBehavior(private val view: View) {

    fun handleScroll(maxScroll: Int, scrollY: Int) {
        with(view) {
            alpha = (one() - (scrollY / maxScroll.toFloat()))
            view.isClickable = alpha.toDouble().greaterOrEqualsThan(0.9)
        }
    }
}