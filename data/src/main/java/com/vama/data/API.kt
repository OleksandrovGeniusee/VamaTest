package com.vama.data

import retrofit2.http.GET
import retrofit2.http.Path

interface API {

    @GET("music/most-played/{paginationSize}/albums.json")
    suspend fun fetchAlbums(
        @Path("paginationSize") paginationSize: Int,
    ): MostPlayedResponse
}