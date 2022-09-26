package com.vama.data.mockedData

import com.vama.domain.models.Album
import com.vama.domain.models.Genre
import java.util.*

val ALBUM = object : Album {
    override fun id() = "1622045624"

    override fun albumName() = "Un Verano Sin Ti"

    override fun artistName() = "Bad Bunny"

    override fun artworkUrl100() = "https://is5-ssl.mzstatic.com/image/thumb/Music112/v4/3e/04/eb/3e04ebf6-370f-f59d-ec84-2c2643db92f1/196626945068.jpg/100x100bb.jpg"

    override fun release() = "2022-05-06"

    override fun releaseDate() = Date()

    override fun copyright() = "Copyright © 2022 Apple Inc. All rights reserved."

    override fun albumUrl() = "https://music.apple.com/us/album/un-verano-sin-ti/1622045624"

    override fun genres() = emptyList<Genre>()
}