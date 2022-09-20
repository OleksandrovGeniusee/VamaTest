package com.vama.domain.albumDataSource

import com.vama.domain.Album

interface AlbumLocalDataSource {

    suspend fun getAlbums(): List<Album>
}