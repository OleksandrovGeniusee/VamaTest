package com.vama.data

import com.vama.data.models.realm.RMAlbum
import com.vama.data.models.realm.RMGenre
import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.types.RealmList
import kotlinx.serialization.Serializable

@Serializable
data class MostPlayedResponse(
    val feed: Feed
)

@Serializable
data class Feed(
    val copyright: String,
    val results: List<AlbumResponse>
)

@Serializable
data class AlbumResponse(
    val id: String,
    val name: String,
    val artistName: String,
    val artworkUrl100: String,
    val releaseDate: String,
    val url: String,
    val genres: List<GenreResponse>
)

@Serializable
data class GenreResponse(
    val genreId: String,
    val name: String,
    val url: String
)

fun AlbumResponse.toRMAlbum(copyright: String): RMAlbum = RMAlbum(
    remoteId = id,
    name = name,
    artistName = artistName,
    releaseDate = releaseDate,
    artworkUrl100 = artworkUrl100,
    genres = genres.toRealmList(),
    url = url,
    copyright = copyright
)

fun List<GenreResponse>.toRealmList(): RealmList<RMGenre> {
    val realmList = realmListOf<RMGenre>()
    forEach {
        realmList.add(
            RMGenre(
                genreId = it.genreId,
                name = it.name,
                url = it.url
            )
        )
    }
    return realmList
}
