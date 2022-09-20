package com.example.vamatest.di

import com.vama.data.albumDataSources.AlbumLocalDataSourceImpl
import com.vama.data.albumDataSources.AlbumRemoteDataSourceImpl
import org.koin.dsl.module

val dataModule
    get() = module {
        factory { AlbumLocalDataSourceImpl() }
        factory { AlbumRemoteDataSourceImpl(get()) }
    }
