package hu.bitnet.smallapp.utils

import hu.bitnet.smallapp.extensions.greaterOrEqualsThan
import hu.bitnet.smallapp.extensions.thousand

object DistanceTextFormatter {

    fun format(distance: Double?): String? {
        return distance?.let {
            var valueToShow = distance
            var unitToShow = DistanceUnit.METER
            if (valueToShow.greaterOrEqualsThan(thousand())) {
                valueToShow /= thousand()
                unitToShow = DistanceUnit.KILOMETER
            }
            "${DecimalTextFormatter.format(valueToShow)} ${unitToShow.unitText}"
        }
    }
}

enum class DistanceUnit(val unitText: String) {
    METER("m"),
    KILOMETER("km")
}