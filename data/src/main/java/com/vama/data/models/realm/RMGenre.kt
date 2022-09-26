package com.vama.data.models.realm

import io.realm.kotlin.types.ObjectId
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey

class RMGenre() : RealmObject {

    @PrimaryKey
    var id: ObjectId = ObjectId.create()

    var genreId: String = ""

    var name: String = ""

    var url: String = ""

    constructor(
        genreId: String,
        name: String,
        url: String,
    ) : this() {
        this.genreId = genreId
        this.name = name
        this.url = url
    }
}
