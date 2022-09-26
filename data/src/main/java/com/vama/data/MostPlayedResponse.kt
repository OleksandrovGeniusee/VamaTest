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
    val results: List<Album>
)

@Serializable
data class Album(
    val id: String,
    val name: String,
    val artistName: String,
    val artworkUrl100: String,
    val releaseDate: String,
    val url: String,
    val genres: List<Genre>
)

@Serializable
data class Genre(
    val genreId: String,
    val name: String,
    val url: String
)

fun Album.toRMAlbum(copyright: String): RMAlbum = RMAlbum(
    remoteId = id,
    name = name,
    artistName = artistName,
    releaseDate = releaseDate,
    artworkUrl100 = artworkUrl100,
    genres = genres.toRealmList(),
    url = url,
    copyright = copyright
)

fun List<Genre>.toRealmList(): RealmList<RMGenre> {
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
