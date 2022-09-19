package com.vama.domain

interface AlbumUseCase {

    suspend fun getAlbums(): List<Album>
}