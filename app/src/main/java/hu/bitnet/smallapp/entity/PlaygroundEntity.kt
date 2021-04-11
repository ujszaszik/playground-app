package hu.bitnet.smallapp.entity

import android.content.Context
import hu.bitnet.smallapp.R
import hu.bitnet.smallapp.database.entity.Playground

open class PlaygroundEntity(context: Context, entity: Playground): BaseEntity() {

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
            BooleanProperty(context.getString(R.string.property_shadowy_title), entity.IsShadowy == "Y"),
            BooleanProperty(context.getString(R.string.property_rainproof_title), entity.IsRainProof == "Y"),
            BooleanProperty(context.getString(R.string.property_fence_title), entity.HasFence == "Y"),
            BooleanProperty(context.getString(R.string.property_free_parking_title), entity.HasFreeParking == "Y"),
            BooleanProperty(context.getString(R.string.property_bench_title), entity.HasBench == "Y"),
            BooleanProperty(context.getString(R.string.property_dust_bin_title), entity.HasDustBin == "Y"),
            HeaderedProperty(context.getString(R.string.property_carpet_title), entity.Carpets ?: context.getString(R.string.property_not_found))
        )
    }
}