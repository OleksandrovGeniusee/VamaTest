package com.vama.data.albumDataSources

import com.vama.data.models.Album
import com.vama.domain.albumDataSource.AlbumLocalDataSource
import io.realm.kotlin.Realm

class AlbumLocalDataSourceImpl : AlbumLocalDataSource<Album> {

    private val config = RealmConfig.REALM

    override suspend fun getAlbums(): List<Album>? {
        val realm: Realm = Realm.open(config)
        return realm.query(Album::class).find()
    }

    override suspend fun getAlbumById(id: String): Album? {
        val realm: Realm = Realm.open(config)
        val album = realm.query(Album::class, "id = '$id'").find().firstOrNull()
        return album
    }

    override suspend fun saveAlbums(items: List<Album>) {
        val realm: Realm = Realm.open(config)

        realm.writeBlocking {
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