package com.vama.data.albumDataSources

import com.vama.data.models.Album
import com.vama.data.models.Genre
import io.realm.kotlin.RealmConfiguration

object RealmConfig {

    val REALM = RealmConfiguration.Builder(schema = setOf(Album::class, Genre::class)).build()
}