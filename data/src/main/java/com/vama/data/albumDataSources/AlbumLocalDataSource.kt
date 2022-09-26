package com.vama.data.albumDataSources

interface AlbumLocalDataSource<T> {

    fun getAlbums(): List<T>?

    suspend fun getAlbumById(id: String): T?

    suspend fun saveAlbums(items: List<T>)
}