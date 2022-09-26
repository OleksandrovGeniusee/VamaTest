package com.vama.data.models

data class Album(
    val id: String,
    val albumName: String,
    val artistName: String,
    val artworkUrl100: String,
    val release: String,
    val copyright: String,
    val albumUrl: String,
    val genres: List<Genre>,
)
