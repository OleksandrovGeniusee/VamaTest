package com.vama.data

import com.vama.data.albumDataSources.AlbumLocalDataSourceImpl
import com.vama.data.albumDataSources.AlbumRemoteDataSourceImpl
import com.vama.domain.Album
import com.vama.domain.AlbumRepository

class AlbumRepositoryImpl(
    private val remoteDataSource: AlbumRemoteDataSourceImpl,
    private val albumDataSource: AlbumLocalDataSourceImpl,
) : AlbumRepository {

    override suspend fun getAlbums(): List<Album> {
        return remoteDataSource.fetchAlbums(100).feed.results
    }
}
