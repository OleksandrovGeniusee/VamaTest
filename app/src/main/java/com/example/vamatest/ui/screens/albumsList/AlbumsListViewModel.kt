package com.example.vamatest.ui.screens.albumsList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.vamatest.baseClasses.BaseViewModel
import com.vama.data.AlbumRepositoryImpl
import com.vama.domain.AlbumRepository
import com.vama.domain.models.Album
import kotlinx.coroutines.flow.collectLatest

class AlbumsListViewModel(private val albumRepositoryImpl: AlbumRepository<Album>) :
    BaseViewModel() {

    val albumsList: LiveData<List<Album>>
        get() = albumsListLiveData

    private var albumsListLiveData: MutableLiveData<List<Album>> = MutableLiveData()

    fun getAlbums() {
        launchWithProgress(show = false) {
            (albumRepositoryImpl as AlbumRepositoryImpl).albumsFlow().collectLatest {
                albumsListLiveData.postValue(it.list.toList())
            }
        }
        launchWithProgress {
            albumRepositoryImpl.fetchAlbums()
        }
    }
}
