package com.vama.domain.albumDataSource

interface AlbumLocalDataSource<T> {

    suspend fun getAlbums(): List<T>?

    suspend fun getAlbumById(id: String): T?

    suspend fun saveAlbums(items: List<T>)
}