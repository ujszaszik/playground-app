package hu.bitnet.smallapp.repository

import android.location.Location
import hu.bitnet.smallapp.entity.PlaygroundEntity
import io.reactivex.Single

interface IPlacesRepository {

    fun getPlaygrounds(location: Location?): Single<List<PlaygroundEntity>>

    fun getPlaygroundById(id: Int): Single<PlaygroundEntity>
}