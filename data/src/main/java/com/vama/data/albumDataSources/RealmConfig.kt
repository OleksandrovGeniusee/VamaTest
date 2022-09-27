package com.vama.data.albumDataSources

import com.vama.data.models.realm.RMAlbum
import com.vama.data.models.realm.RMGenre
import io.realm.kotlin.RealmConfiguration

object RealmConfig {

    val REALM = RealmConfiguration.Builder(schema = setOf(RMAlbum::class, RMGenre::class)).build()
}