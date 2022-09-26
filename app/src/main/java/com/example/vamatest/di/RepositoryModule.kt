package com.example.vamatest.di

import com.vama.data.AlbumRepositoryImpl
import com.vama.data.models.Album
import com.vama.domain.AlbumRepository
import org.koin.dsl.module

val repositoryModule
    get() = module {
        single<AlbumRepository<Album>> {
            AlbumRepositoryImpl(get(), get())
        }
    }
