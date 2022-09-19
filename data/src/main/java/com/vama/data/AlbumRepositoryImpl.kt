package com.vama.data

import com.vama.domain.Album
import com.vama.domain.AlbumDataSource
import com.vama.domain.AlbumRepository

class AlbumRepositoryImpl(private val dataSource: AlbumDataSource) : AlbumRepository {

    override suspend fun getAlbums(): List<Album> {
        return dataSource.getAlbums()
    }
}