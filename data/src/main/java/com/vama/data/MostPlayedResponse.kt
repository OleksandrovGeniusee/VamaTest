package com.vama.data

import com.google.gson.annotations.SerializedName
import com.vama.data.models.Album

data class MostPlayedResponse(
    @SerializedName("feed") val feed: Feed,
)

data class Feed(
    @SerializedName("copyright") val copyright: String,
    @SerializedName("results") val results: List<Album>,
)
