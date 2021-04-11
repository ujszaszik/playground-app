package hu.bitnet.smallapp.entity

import android.content.Context
import hu.bitnet.smallapp.R
import hu.bitnet.smallapp.database.entity.IndoorPlayground

class IndoorPlaygroundEntity(context: Context, entity: IndoorPlayground): BaseEntity() {

    init {
        id = entity.Id!!
        name = entity.Name
        photo = entity.Photo
        zipCode = entity.ZipCode!!
        city = entity.City
        address = entity.Address
        latitude = entity.Latitude!!
        longitude = entity.Longitude!!
        description = entity.Description
        isActive = entity.IsActive == "Y"
        properties = listOf(
            BooleanProperty(context.getString(R.string.property_shadowy_title), entity.IsRoofed == "Y"),
            if((entity.FreeParking?.length ?: 0) > 1) {
                HeaderedProperty(context.getString(R.string.property_free_parking_title), entity.FreeParking!!)
            } else {
                BooleanProperty(context.getString(R.string.property_free_parking_title), entity.FreeParking == "Y")
            },
            HeaderedProperty(context.getString(R.string.property_rainproof_title), entity.Cover ?: context.getString(R.string.property_not_found)),
            HeaderedProperty(context.getString(R.string.property_fence_title), entity.Tickets ?: context.getString(R.string.property_not_found)),
            HeaderedProperty(context.getString(R.string.property_bench_title), entity.Age ?: context.getString(R.string.property_not_found))
        )
    }

}