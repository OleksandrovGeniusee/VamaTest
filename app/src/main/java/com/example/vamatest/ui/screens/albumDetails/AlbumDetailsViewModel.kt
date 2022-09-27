package com.example.vamatest.ui.screens.albumDetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.vamatest.baseClasses.BaseViewModel
import com.vama.domain.models.Album
import com.vama.domain.AlbumRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AlbumDetailsViewModel(private val albumRepositoryImpl: AlbumRepository<Album>) :
    BaseViewModel() {

    val album: LiveData<Album>
        get() = albumLiveData

    private var albumLiveData: MutableLiveData<Album> = MutableLiveData()

    private var albumId: String? = null

    fun setAlbum(albumId: String?) {
        if (this.albumId == albumId) return
        this.albumId = albumId
        this.viewModelScope.launch(Dispatchers.IO) {
            albumLiveData.postValue(albumId?.let { albumRepositoryImpl.getAlbumById(it) })
        }
    }
}
