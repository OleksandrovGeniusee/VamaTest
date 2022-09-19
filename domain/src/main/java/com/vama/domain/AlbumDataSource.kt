package com.vama.domain

interface AlbumDataSource {

    suspend fun getAlbums(): List<Album>
}