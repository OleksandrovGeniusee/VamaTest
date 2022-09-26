package com.vama.data.albumDataSources

import com.vama.data.API
import com.vama.data.common.extensions.dateFromStandardString
import com.vama.data.models.Album
import com.vama.domain.albumDataSource.AlbumRemoteDataSource

class AlbumRemoteDataSourceImpl(private val api: API) : AlbumRemoteDataSource<Album> {

    override suspend fun fetchAlbums(paginationSize: Int): List<Album> {
        val feed = api.fetchAlbums(paginationSize).feed
        val albums = feed.results.map {
            Album(
                id = it.id,
                name = it.name,
                artistName = it.artistName,
                releaseDate = it.releaseDate,
                kind = it.kind,
                artistId = it.artistId,
                artistUrl = it.artistUrl,
                contentAdvisoryRating = it.contentAdvisoryRating,
                artworkUrl100 = it.artworkUrl100,
                genresRespose = it.genresRespose,
                url = it.url,
                copyright = feed.copyright,
                release = it.releaseDate?.let { it1 -> dateFromStandardString(it1)?.time }
            )
        }
        return albums
    }
}