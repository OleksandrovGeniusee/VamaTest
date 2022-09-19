package com.vama.domain

interface AlbumRepository {

    suspend fun getAlbums(): List<Album>
}