package hu.bitnet.smallapp.entity

import hu.bitnet.smallapp.extensions.empty

open class BaseEntity {

    var id: Int = 0

    var name: String? = String.empty

    var photo: String? = String.empty

    var zipCode: Int = 0

    var city: String? = String.empty

    var address: String? = String.empty

    var latitude: Double = 0.0

    var longitude: Double = 0.0

    var description: String? = String.empty

    var isActive: Boolean = false

    var properties: List<BaseProperty> = listOf()

    val fullAddress: String
        get() {
            return String.format("%d, %s, %s", zipCode, city, address)
        }

}