package com.vama.domain.albumDataSource

import com.vama.domain.MostPlayedResponse

interface AlbumRemoteDataSource {

    suspend fun fetchAlbums(paginationSize: Int): MostPlayedResponse
}