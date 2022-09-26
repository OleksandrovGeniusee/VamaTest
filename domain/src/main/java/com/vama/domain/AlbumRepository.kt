package com.vama.domain

interface AlbumRepository<T> {

    suspend fun getAlbumById(albumId: String): T?

    suspend fun fetchAlbums()
}