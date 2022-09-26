package com.vama.data.albumDataSources

interface AlbumRemoteDataSource<T> {

    suspend fun fetchAlbums(paginationSize: Int): List<T>
}