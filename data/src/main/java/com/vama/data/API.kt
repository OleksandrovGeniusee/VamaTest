package com.vama.data

import com.vama.domain.Album
import retrofit2.http.GET

interface API {

    @GET("music/most-played/50/albums.json")
    suspend fun fetchAlbums(): List<Album>
}