package com.vama.domain.albumDataSource

interface AlbumRemoteDataSource<T> {

    suspend fun fetchAlbums(paginationSize: Int): List<T>
}