package com.example.vamatest.ui.screens.albumsList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.vamatest.baseClasses.BaseViewModel
import com.vama.data.models.Album
import com.vama.domain.AlbumRepository

class AlbumsListViewModel(private val albumRepositoryImpl: AlbumRepository<Album>) :
    BaseViewModel() {

    val albumsList: LiveData<List<Album>>
        get() = albumsListLiveData

    private var albumsListLiveData: MutableLiveData<List<Album>> = MutableLiveData()

    init {
        launchWithProgress(show = false) {
            albumRepositoryImpl.albums.collect {
                albumsListLiveData.postValue(it)
            }
        }
    }

    fun updateAlbums() {
        launchWithProgress {
            albumRepositoryImpl.fetchAlbums()
        }
    }
}
