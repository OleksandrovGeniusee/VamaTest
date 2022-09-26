package com.vama.data.models

import com.google.gson.annotations.SerializedName
import com.vama.domain.models.Genre
import io.realm.kotlin.types.ObjectId
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey

class Genre() : RealmObject, Genre {

    @PrimaryKey
    var _id: ObjectId = ObjectId.create()

    @SerializedName("genreId")
    var genreId: String? = null

    @SerializedName("name")
    var name: String? = null

    @SerializedName("url")
    var url: String? = null

    constructor(
        genreId: String? = null,
        name: String? = null,
        url: String? = null,
    ) : this() {
        this.genreId = genreId
        this.name = name
        this.url = url
    }

    override fun genreId(): String? {
        return genreId
    }

    override fun name(): String? {
        return name
    }

    override fun url(): String? {
        return url
    }
}
