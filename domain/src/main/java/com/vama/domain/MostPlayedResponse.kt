package com.vama.domain

import com.google.gson.annotations.SerializedName

data class MostPlayedResponse(
    @SerializedName("feed") val feed: Feed,
)

data class Feed(
    @SerializedName("results") val results: List<Album>,
)
