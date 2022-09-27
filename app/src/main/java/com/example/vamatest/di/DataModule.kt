package com.example.vamatest.di

import com.vama.data.albumDataSources.AlbumLocalDataSourceImpl
import com.vama.data.albumDataSources.AlbumRemoteDataSourceImpl
import com.vama.data.models.realm.RMAlbum
import com.vama.data.models.realm.RMGenre
import io.realm.kotlin.RealmConfiguration
import org.koin.dsl.module

val dataModule
    get() = module {
        single {
            AlbumLocalDataSourceImpl(
                RealmConfiguration.Builder(
                    schema = setOf(
                        RMAlbum::class,
                        RMGenre::class
                    )
                ).build()
            )
        }
        factory { AlbumRemoteDataSourceImpl(get()) }
    }
