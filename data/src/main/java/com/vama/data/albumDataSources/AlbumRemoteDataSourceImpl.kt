package com.vama.data.albumDataSources

import com.vama.data.API
import com.vama.domain.albumDataSource.AlbumRemoteDataSource

class AlbumRemoteDataSourceImpl(private val api: API) : AlbumRemoteDataSource {

    override suspend fun fetchAlbums(paginationSize: Int) = api.fetchAlbums(paginationSize)
}