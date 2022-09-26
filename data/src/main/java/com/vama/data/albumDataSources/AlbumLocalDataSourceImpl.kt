package com.vama.data.albumDataSources

import com.vama.data.models.Album
import com.vama.data.models.Genre
import com.vama.domain.albumDataSource.AlbumLocalDataSource
import io.realm.kotlin.Realm
import io.realm.kotlin.query.RealmResults

class AlbumLocalDataSourceImpl : AlbumLocalDataSource<Album> {

    private val config = RealmConfig.REALM

    override suspend fun getAlbums() = Realm.open(config).query(Album::class).find()

    override suspend fun getAlbumById(id: String) =
        Realm.open(config).query(Album::class, "id = '$id'").find().firstOrNull()

    override suspend fun saveAlbums(items: List<Album>) {
        val realm: Realm = Realm.open(config)

        realm.writeBlocking {
            val albums: RealmResults<Album> = this.query(Album::class).find()
            delete(albums)

            val genres: RealmResults<Genre> = this.query(Genre::class).find()
            delete(genres)

            items.forEach {
                copyToRealm(it)
                it.genresRespose?.forEach { genre ->
                    copyToRealm(genre)
                }
            }
        }
        realm.close()
    }
}