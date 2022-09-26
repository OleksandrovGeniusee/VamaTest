package com.example.vamatest.di

import com.vama.data.albumDataSources.AlbumLocalDataSourceImpl
import com.vama.data.albumDataSources.AlbumRemoteDataSourceImpl
import com.vama.data.albumDataSources.RealmConfig
import org.koin.dsl.module

val dataModule
    get() = module {
        single { AlbumLocalDataSourceImpl(RealmConfig.REALM) }
        factory { AlbumRemoteDataSourceImpl(get()) }
    }
