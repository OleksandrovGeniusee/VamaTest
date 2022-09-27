package com.vama.data.models.realm

import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.types.ObjectId
import io.realm.kotlin.types.RealmList
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey

class RMAlbum() : RealmObject {

    @PrimaryKey
    var id: ObjectId = ObjectId.create()

    var remoteId: String = ""

    var name: String = ""

    var artistName: String = ""

    var releaseDate: String = ""

    var artworkUrl100: String = ""

    var genres: RealmList<RMGenre> = realmListOf()

    var url: String = ""

    var copyright: String = ""

    constructor(
        remoteId: String,
        name: String,
        artistName: String,
        releaseDate: String,
        artworkUrl100: String,
        genres: RealmList<RMGenre>,
        url: String,
        copyright: String,
    ) : this() {
        this.remoteId = remoteId
        this.name = name
        this.artistName = artistName
        this.releaseDate = releaseDate
        this.artworkUrl100 = artworkUrl100
        this.genres = genres
        this.url = url
        this.copyright = copyright
    }
}
