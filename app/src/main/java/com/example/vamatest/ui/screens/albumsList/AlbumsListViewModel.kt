package com.example.vamatest.ui.screens.albumsList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.vamatest.baseClasses.BaseViewModel
import com.vama.domain.Album
import com.vama.domain.AlbumRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AlbumsListViewModel(private val albumRepositoryImpl: AlbumRepository) : BaseViewModel() {

    val isRefreshing: LiveData<Boolean>
        get() = isRefreshingLiveData

    val albumsList: LiveData<List<Album>>
        get() = albumsListLiveData

    private var albumsListLiveData: MutableLiveData<List<Album>> = MutableLiveData()
    private var isRefreshingLiveData: MutableLiveData<Boolean> = MutableLiveData(false)

    fun getAlbums() {
        this.viewModelScope.launch(Dispatchers.IO) {
            isRefreshingLiveData.postValue(true)
            albumsListLiveData.postValue(albumRepositoryImpl.getAlbums())
            isRefreshingLiveData.postValue(false)
        }
    }
}