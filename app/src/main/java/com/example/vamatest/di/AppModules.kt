package com.example.vamatest.di

import android.content.Context
import org.koin.android.ext.koin.androidContext
import org.koin.core.KoinApplication

val appModules = listOf(
    dataModule,
    repositoryModule,
)

fun KoinApplication.addAppModule(applicationContext: Context) = apply {
    androidContext(applicationContext)
    modules(appModules)
}