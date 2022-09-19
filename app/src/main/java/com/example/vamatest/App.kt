package com.example.vamatest

import android.app.Application
import android.util.Log
import com.example.vamatest.di.addAppModule
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class App : Application(), CoroutineScope {

    override val coroutineContext = SupervisorJob() +
            CoroutineExceptionHandler { _, throwable ->
                Log.e("error", throwable.toString())
            }

    override fun onCreate() {
        super.onCreate()
        startKoin()
    }

    private fun startKoin() {
        org.koin.core.context.startKoin { addAppModule(this@App) }
    }

}