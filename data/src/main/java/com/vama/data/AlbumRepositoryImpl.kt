package com.vama.data

import com.vama.data.albumDataSources.AlbumLocalDataSourceImpl
import com.vama.data.albumDataSources.AlbumRemoteDataSourceImpl
import com.vama.data.common.extensions.dateFromStandardString
import com.vama.domain.Album
import com.vama.domain.AlbumRepository

class AlbumRepositoryImpl(
    private val remoteDataSource: AlbumRemoteDataSourceImpl,
    private val albumDataSource: AlbumLocalDataSourceImpl
) : AlbumRepository {

    override suspend fun getAlbums(): List<Album> {
        val feed = remoteDataSource.fetchAlbums(100).feed
        return feed.results.map {
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
                genres = it.genres,
                url = it.url,
                copyright = feed.copyright,
                release = dateFromStandardString(it.releaseDate)
            )
        }
    }
}
