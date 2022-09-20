package com.example.vamatest.di

import com.example.vamatest.ui.screens.albumsList.AlbumsListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule
    get() = module {
        viewModel {
            AlbumsListViewModel(get())
        }
    }