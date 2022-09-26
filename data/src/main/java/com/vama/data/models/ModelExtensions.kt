package com.vama.data.models

import com.vama.data.models.realm.RMAlbum
import com.vama.data.models.realm.RMGenre

fun RMAlbum.toAlbum(): Album = Album(
    id = this.remoteId,
    albumName = this.name,
    artistName = this.artistName,
    artworkUrl100 = this.artworkUrl100,
    release = this.releaseDate,
    copyright = this.copyright,
    albumUrl = this.url,
    genres = this.genres.map { it.toGenre() }
)

fun RMGenre.toGenre(): Genre = Genre(
    id = genreId,
    name = name,
    url = url
)
