package com.vama.data

import com.vama.data.albumDataSources.AlbumLocalDataSourceImpl
import com.vama.data.albumDataSources.AlbumRemoteDataSourceImpl
import com.vama.data.albumDataSources.RealmConfig
import com.vama.data.models.Album
import com.vama.domain.AlbumRepository
import io.realm.kotlin.Realm
import io.realm.kotlin.notifications.ResultsChange
import kotlinx.coroutines.flow.Flow

class AlbumRepositoryImpl(
    private val remoteDataSource: AlbumRemoteDataSourceImpl,
    private val localDataSource: AlbumLocalDataSourceImpl
) : AlbumRepository<Album> {

    override suspend fun fetchAlbums() {
        val albums = remoteDataSource.fetchAlbums(100)
        localDataSource.saveAlbums(albums)
    }

    override suspend fun getAlbumById(albumId: String): Album? {
        return localDataSource.getAlbumById(albumId)
    }

    fun albumsFlow(): Flow<ResultsChange<Album>> {
        val realm: Realm = Realm.open(RealmConfig.REALM)
        return realm.query(Album::class).find().asFlow()
    }
}
