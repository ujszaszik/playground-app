package hu.bitnet.smallapp.repository

import android.content.Context
import android.location.Location
import hu.bitnet.smallapp.database.ICoreDatabase
import hu.bitnet.smallapp.entity.PlaygroundEntity
import hu.bitnet.smallapp.entity.error.EntityIsNullException
import hu.bitnet.smallapp.utils.playgroundComparator
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class PlacesRepository
@Inject
constructor(
    private val context: Context,
    private val database: ICoreDatabase
) : IPlacesRepository {

    override fun getPlaygrounds(location: Location?): Single<List<PlaygroundEntity>> {
        return Single.create<List<PlaygroundEntity>> { emitter ->
            database.getPlaygrounds()
                .map { entity -> PlaygroundEntity(context, entity) }
                .sorted(playgroundComparator(location))
                .toList()
                .subscribe { list -> emitter.onSuccess(list) }
        }.subscribeOn(Schedulers.io())
    }

    override fun getPlaygroundById(id: Int): Single<PlaygroundEntity> {
        return Single.create<PlaygroundEntity> { emitter ->
            database.getPlaygrounds()
                .map { entity -> PlaygroundEntity(context, entity) }
                .filter { entity -> id == entity.id }
                .singleOrError()
                .subscribe(
                    { playground -> emitter.onSuccess(playground) },
                    { emitter.onError(EntityIsNullException(id)) })
        }.subscribeOn(Schedulers.io())
    }
}