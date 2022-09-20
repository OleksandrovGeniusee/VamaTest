package com.example.vamatest.ui.screens.albumsList

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.vamatest.baseClasses.BaseViewModel
import com.vama.domain.Album
import com.vama.domain.AlbumRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AlbumsListViewModel(private val albumRepositoryImpl: AlbumRepository) : BaseViewModel() {

    val albumsList: LiveData<List<Album>>
        get() = albumsListLiveData

    private var albumsListLiveData: MutableLiveData<List<Album>> = MutableLiveData()

    fun getAlbums() {
        this.viewModelScope.launch(Dispatchers.IO) {
            albumsListLiveData.postValue(albumRepositoryImpl.getAlbums())
        }
    }
}