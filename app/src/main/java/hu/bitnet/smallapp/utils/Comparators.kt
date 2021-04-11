package hu.bitnet.smallapp.utils

import android.location.Location
import hu.bitnet.smallapp.entity.BaseProperty
import hu.bitnet.smallapp.entity.HeaderedProperty
import hu.bitnet.smallapp.entity.PlaygroundEntity
import hu.bitnet.smallapp.extensions.distanceBetween

val propertyComparator =
    Comparator<BaseProperty> { prop, otherProp ->
        if (prop is HeaderedProperty && otherProp !is HeaderedProperty) -1
        else if (prop !is HeaderedProperty && otherProp is HeaderedProperty) 1
        else 0
    }

fun playgroundComparator(location: Location?) =
    Comparator<PlaygroundEntity> { playground, other ->
        location?.distanceBetween(playground)?.compareTo(location.distanceBetween(other))
            ?: playground.id.toFloat().compareTo(other.id.toFloat())
    }