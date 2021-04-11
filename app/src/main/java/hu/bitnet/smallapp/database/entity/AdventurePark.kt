package hu.bitnet.smallapp.database.entity

import hu.bitnet.smallapp.extensions.empty
import io.realm.RealmObject
import io.realm.annotations.RealmClass
import io.realm.annotations.RealmField

@RealmClass("adventure_parks")
open class AdventurePark : RealmObject() {

    @RealmField("_id")
    var Id: Int? = 0

    @RealmField("name")
    var Name: String? = String.empty

    @RealmField("photo")
    var Photo: String? = String.empty

    @RealmField("zip_code")
    var ZipCode: Int? = 0

    @RealmField("city")
    var City: String? = String.empty

    @RealmField("county")
    var County: String? = String.empty

    @RealmField("address")
    var Address: String? = String.empty

    @RealmField("latitude")
    var Latitude: Double? = 0.0

    @RealmField("longitude")
    var Longitude: Double? = 0.0

    @RealmField("description")
    var Description: String? = String.empty

    @RealmField("carpet")
    var Carpets: String? = String.empty

    @RealmField("shadowy")
    var IsShadowy: String? = String.empty

    @RealmField("rainproof")
    var IsRainProof: String? = String.empty

    @RealmField("fence")
    var HasFence: String? = String.empty

    @RealmField("free_parking")
    var HasFreeParking: String? = String.empty

    @RealmField("bench")
    var HasBench: String? = String.empty

    @RealmField("dust_bin")
    var HasDustBin: String? = String.empty

    @RealmField("active")
    var IsActive: String? = String.empty

    @RealmField("prices")
    var Prices: String? = String.empty

}