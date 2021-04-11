package hu.bitnet.smallapp.database

import hu.bitnet.smallapp.database.entity.Playground
import io.reactivex.Observable

interface ICoreDatabase {

    fun getPlaygrounds(): Observable<Playground>

}