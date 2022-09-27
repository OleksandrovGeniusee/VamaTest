package com.vama.data.albumDataSources

import com.vama.data.API
import com.vama.data.models.realm.RMAlbum
import com.vama.data.toRMAlbum

class AlbumRemoteDataSourceImpl(private val api: API) : AlbumRemoteDataSource<RMAlbum> {

    override suspend fun fetchAlbums(paginationSize: Int): List<RMAlbum> {
        val feed = api.fetchAlbums(paginationSize).feed
        return feed.results.map { it.toRMAlbum(feed.copyright) }
    }
}
