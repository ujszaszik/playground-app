package hu.bitnet.smallapp.database.entity

import hu.bitnet.smallapp.extensions.empty
import io.realm.RealmObject
import io.realm.annotations.RealmClass
import io.realm.annotations.RealmField

@RealmClass(name = "playgrounds")
open class Playground(

    @RealmField(name = "_id")
    var Id: Int? = 0,

    @RealmField(name = "name")
    var Name: String? = String.empty,

    @RealmField(name = "photo")
    var Photo: String? = String.empty,

    @RealmField(name = "zip_code")
    var ZipCode: Int? = 0,

    @RealmField(name = "city")
    var City: String? = String.empty,

    @RealmField(name = "county")
    var County: String? = String.empty,

    @RealmField(name = "address")
    var Address: String? = String.empty,

    @RealmField(name = "latitude")
    var Latitude: Double? = 0.0,

    @RealmField(name = "longitude")
    var Longitude: Double? = 0.0,

    @RealmField(name = "description")
    var Description: String? = String.empty,

    @RealmField(name = "carpet")
    var Carpets: String? = String.empty,

    @RealmField(name = "shadowy")
    var IsShadowy: String? = String.empty,

    @RealmField(name = "rainproof")
    var IsRainProof: String? = String.empty,

    @RealmField(name = "fence")
    var HasFence: String? = String.empty,

    @RealmField(name = "free_parking")
    var HasFreeParking: String? = String.empty,

    @RealmField(name = "bench")
    var HasBench: String? = String.empty,

    @RealmField(name = "dust_bin")
    var HasDustBin: String? = String.empty,

    @RealmField(name = "active")
    var IsActive: String? = String.empty

): RealmObject()