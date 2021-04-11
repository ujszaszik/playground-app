package hu.bitnet.smallapp.database

import android.content.Context
import hu.bitnet.smallapp.database.entity.AdventurePark
import hu.bitnet.smallapp.database.entity.IndoorPlayground
import hu.bitnet.smallapp.database.entity.Playground
import io.reactivex.Observable
import io.realm.Realm
import io.realm.RealmConfiguration
import io.realm.annotations.RealmModule
import javax.inject.Inject

class CoreDatabase
@Inject
constructor(context: Context) : ICoreDatabase {

    private val config: RealmConfiguration

    init {
        Realm.init(context)
        config = RealmConfiguration.Builder()
            .assetFile("porontypont.realm")
            .readOnly()
            .modules(BundledRealmModule())
            .build()
    }

    override fun getPlaygrounds(): Observable<Playground> {
        return Observable.create { emitter ->
            Realm.getInstance(config)
                .where(Playground::class.java)
                .findAll()
                .forEach { emitter.onNext(it) }
            emitter.onComplete()
        }
    }
}

@RealmModule(classes = [Playground::class, IndoorPlayground::class, AdventurePark::class])
class BundledRealmModule