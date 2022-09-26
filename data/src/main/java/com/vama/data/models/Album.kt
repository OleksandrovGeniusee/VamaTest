package com.vama.data.models

import com.google.gson.annotations.SerializedName
import com.vama.domain.models.Album
import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.types.ObjectId
import io.realm.kotlin.types.RealmList
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.Ignore
import io.realm.kotlin.types.annotations.PrimaryKey
import java.util.*

class Album() : RealmObject, Album {

    @PrimaryKey
    var _id: ObjectId = ObjectId.create()

    @SerializedName("id")
    var id: String? = null

    @SerializedName("name")
    var name: String? = null

    @SerializedName("artistName")
    var artistName: String? = null

    @SerializedName("releaseDate")
    var releaseDate: String? = null

    @SerializedName("kind")
    var kind: String? = null

    @SerializedName("artistId")
    var artistId: String? = null

    @SerializedName("artistUrl")
    var artistUrl: String? = null

    @SerializedName("contentAdvisoryRating")
    var contentAdvisoryRating: String? = null

    @SerializedName("artworkUrl100")
    var artworkUrl100: String? = null

    @Ignore
    @SerializedName("genres")
    var genresRespose: List<Genre>? = null

    var genresRealm: RealmList<Genre> = realmListOf()

    @SerializedName("url")
    var url: String? = null

    var copyright: String? = null
    var release: Long? = null

    constructor(
        id: String? = null,
        name: String? = null,
        artistName: String? = null,
        releaseDate: String? = null,
        kind: String? = null,
        artistId: String? = null,
        artistUrl: String? = null,
        contentAdvisoryRating: String? = null,
        artworkUrl100: String? = null,
        genresRespose: List<Genre>? = null,
        url: String? = null,
        copyright: String? = null,
        release: Long? = null
    ) : this() {
        this.id = id
        this.name = name
        this.artistName = artistName
        this.releaseDate = releaseDate
        this.kind = kind
        this.artistId = artistId
        this.artistUrl = artistUrl
        this.contentAdvisoryRating = contentAdvisoryRating
        this.artworkUrl100 = artworkUrl100
        genresRespose?.forEach {
            this.genresRealm.add(it)
        }
        this.url = url
        this.copyright = copyright
        this.release = release
    }

    override fun id(): String? {
        return id
    }

    override fun albumName(): String? {
        return name
    }

    override fun artistName(): String? {
        return artistName
    }

    override fun artworkUrl100(): String? {
        return artworkUrl100
    }

    override fun release(): String? {
        return releaseDate
    }

    override fun releaseDate(): Date? {
        release?.let {
            return Date().apply {
                time = it
            }
        }
        return null
    }

    override fun copyright(): String? {
        return copyright
    }

    override fun albumUrl(): String? {
        return url
    }

    override fun genres(): List<Genre>? {
        return genresRealm
    }
}
