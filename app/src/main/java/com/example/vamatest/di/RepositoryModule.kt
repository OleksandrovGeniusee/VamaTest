package com.example.vamatest.di

import com.vama.data.AlbumRepositoryImpl
import com.vama.domain.models.Album
import com.vama.domain.AlbumRepository
import org.koin.dsl.module

val repositoryModule
    get() = module {
        single<AlbumRepository<Album>> {
            AlbumRepositoryImpl(get(), get())
        }
    }
