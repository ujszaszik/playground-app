package hu.bitnet.smallapp.utils

import hu.bitnet.smallapp.extensions.comma
import hu.bitnet.smallapp.extensions.empty
import hu.bitnet.smallapp.extensions.two
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols

object DecimalTextFormatter {

    private val symbols = DecimalFormatSymbols().apply {
        decimalSeparator = Char.comma
    }

    private val decimalFormat = DecimalFormat(String.empty, symbols).apply {
        maximumFractionDigits = two()
    }

    fun format(number: Double): String = decimalFormat.format(number)
}