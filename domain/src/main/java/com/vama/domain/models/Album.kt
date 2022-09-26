package com.vama.domain.models

import java.util.*

interface Album {

    fun id(): String?

    fun albumName(): String?

    fun artistName(): String?

    fun artworkUrl100(): String?

    fun release(): String?

    fun releaseDate(): Date?

    fun copyright(): String?

    fun albumUrl(): String?

    fun genres(): List<Genre>?
}