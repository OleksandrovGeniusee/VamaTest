package com.vama.data

import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val networkModule

    get() = module {
        single {
            OkHttpClient().newBuilder()
                .connectTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
                .build()
        }

        factory {
            val baseURL = "https://rss.applemarketingtools.com/api/v2/us/"
            Retrofit.Builder()
                .baseUrl(baseURL)
                .client(get())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }