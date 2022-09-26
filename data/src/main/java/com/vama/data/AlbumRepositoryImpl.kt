package com.vama.data

import com.vama.data.albumDataSources.AlbumLocalDataSourceImpl
import com.vama.data.albumDataSources.AlbumRemoteDataSourceImpl
import com.vama.data.models.Album
import com.vama.data.models.toAlbum
import com.vama.domain.AlbumRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class AlbumRepositoryImpl(
    private val remoteDataSource: AlbumRemoteDataSourceImpl,
    private val localDataSource: AlbumLocalDataSourceImpl
) : AlbumRepository<Album> {

    override suspend fun fetchAlbums() {
        val albums = remoteDataSource.fetchAlbums(100)
        localDataSource.saveAlbums(albums)
    }

    override suspend fun getAlbumById(albumId: String): Album? {
        val rmAlbum = localDataSource.getAlbumById(albumId)
        return rmAlbum?.toAlbum() ?: null
    }

    override val albums: Flow<List<Album>>
        get() = localDataSource.getAlbums().asFlow().map {
            it.list.map { rmAlbum -> rmAlbum.toAlbum() }
        }
}
