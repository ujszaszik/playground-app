package hu.bitnet.smallapp.database.entity

import hu.bitnet.smallapp.extensions.empty
import io.realm.RealmObject
import io.realm.annotations.RealmClass
import io.realm.annotations.RealmField

@RealmClass("indoor_playgrounds")
open class IndoorPlayground: RealmObject() {

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

    @RealmField("covered")
    var Cover: String? = String.empty

    @RealmField("roofed")
    var IsRoofed: String? = String.empty

    @RealmField("free_parking")
    var FreeParking: String? = String.empty

    @RealmField("tickets")
    var Tickets: String? = String.empty

    @RealmField("age")
    var Age: String? = String.empty

    @RealmField("active")
    var IsActive: String? = String.empty

}