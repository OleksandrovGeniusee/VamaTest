package com.example.vamatest.ui.screens.albumDetails

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.vamatest.baseClasses.BaseViewModel
import com.vama.domain.Album
import com.vama.domain.Gener
import java.util.*

class AlbumDetailsViewModel : BaseViewModel() {

    val album: LiveData<Album>
        get() = albumLiveData

    private var albumLiveData: MutableLiveData<Album> = MutableLiveData()

    private var albumId: String? = null

    fun setAlbum(albumId: String?) {
        if (this.albumId == albumId) return
        this.albumId = albumId
        albumLiveData.postValue(
            Album(
                id = "1622045624",
                name = "Un Verano Sin Ti",
                artistName = "Bad Bunny",
                releaseDate = "2022-05-06",
                kind = "albums",
                artistId = "1126808565",
                artistUrl = "https://music.apple.com/us/artist/bad-bunny/1126808565",
                contentAdvisoryRating = "Explict",
                artworkUrl100 = "https://is5-ssl.mzstatic.com/image/thumb/Music112/v4/3e/04/eb/3e04ebf6-370f-f59d-ec84-2c2643db92f1/196626945068.jpg/100x100bb.jpg",
                genres = listOf(
                    Gener(
                        genreId = "id",
                        name = "Hip Hop",
                        url = "https://music.apple.com/us/artist/bad-bunny/1126808565"
                    ),
                    Gener(
                        genreId = "id1",
                        name = "Rap",
                        url = "https://music.apple.com/us/artist/bad-bunny/1126808565"
                    )
                ),
                url = "https://music.apple.com/us/album/un-verano-sin-ti/1622045624",
                copyright = "Copyright © 2022 Apple Inc. All rights reserved.",
                release = Date(),
            )
        )
    }
}
