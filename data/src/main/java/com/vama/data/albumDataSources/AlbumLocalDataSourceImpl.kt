package com.vama.data.albumDataSources

import com.vama.domain.Album
import com.vama.domain.albumDataSource.AlbumLocalDataSource

class AlbumLocalDataSourceImpl : AlbumLocalDataSource {

    override suspend fun getAlbums(): List<Album> {
        return emptyList()
    }
}