package com.vama.data.albumDataSources

import com.vama.data.models.realm.RMAlbum
import com.vama.data.models.realm.RMGenre
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import io.realm.kotlin.UpdatePolicy
import io.realm.kotlin.query.RealmResults

class AlbumLocalDataSourceImpl(private val config: RealmConfiguration) :
    AlbumLocalDataSource<RMAlbum> {

    override fun getAlbums() = Realm.open(config).query(RMAlbum::class).find()

    override suspend fun getAlbumById(id: String) =
        Realm.open(config).query(RMAlbum::class, "remoteId = '$id'").find().firstOrNull()

    override suspend fun saveAlbums(items: List<RMAlbum>) {
        val realm: Realm = Realm.open(config)

        realm.writeBlocking {
            val albums: RealmResults<RMAlbum> = this.query(RMAlbum::class).find()
            delete(albums)

            val genres: RealmResults<RMGenre> = this.query(RMGenre::class).find()
            delete(genres)

            items.forEach {
                copyToRealm(it, UpdatePolicy.ALL)
                it.genres.forEach { genre ->
                    copyToRealm(genre, UpdatePolicy.ALL)
                }
            }
        }
        realm.close()
    }
}
