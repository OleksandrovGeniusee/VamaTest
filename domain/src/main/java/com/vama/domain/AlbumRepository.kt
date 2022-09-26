package com.vama.domain

import kotlinx.coroutines.flow.Flow

interface AlbumRepository<T> {

    val albums: Flow<List<T>>

    suspend fun getAlbumById(albumId: String): T?

    suspend fun fetchAlbums()
}