package hu.bitnet.smallapp.extensions

import android.location.Location
import android.net.Uri
import hu.bitnet.smallapp.entity.PlaygroundEntity


fun Location.distanceBetween(entity: PlaygroundEntity?): Float {
    return entity?.let {
        val results = FloatArray(3)
        Location.distanceBetween(latitude, longitude, it.latitude, it.longitude, results)
        results.first()
    } ?: zero()
}

fun geoUriFromEntity(latitude: Double, longitude: Double, name: String): Uri {
    return Uri.parse("geo:0,0?q=$latitude,$longitude($name)")
}