package com.vama.domain

import com.google.gson.annotations.SerializedName

data class MostPlayedResponse(
    @SerializedName("feed") val feed: Feed,
)

data class Feed(
    @SerializedName("copyright") val copyright: String,
    @SerializedName("results") val results: List<Album>,
)

data class Gener(
    @SerializedName("genreId") val genreId: String,
    @SerializedName("name") val name: String,
    @SerializedName("url") val url: String,
)
