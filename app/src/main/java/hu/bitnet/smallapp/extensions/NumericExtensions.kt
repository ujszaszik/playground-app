package hu.bitnet.smallapp.extensions

fun zero() = 0.0F

fun one() = 1.0F

fun two() = 2

fun Number.equalsOne(): Boolean = this == 1

fun Number.lessThan(value: Number): Boolean = this.toDouble() < value.toDouble()

fun Double.greaterOrEqualsThan(other: Number): Boolean = this >= other.toDouble()

fun thousand() = 1000