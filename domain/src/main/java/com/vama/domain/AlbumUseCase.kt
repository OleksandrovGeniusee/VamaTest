package com.vama.domain

interface AlbumUseCase<T> {

    suspend fun getAlbums(): List<T>
}