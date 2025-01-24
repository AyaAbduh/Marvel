package com.example.game.di

import com.example.marvel.data.TheDBInterface
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


const val BASE_URL = "https://gateway.marvel.com/"

object AppModule {
    fun getClient(): TheDBInterface {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(TheDBInterface::class.java)
    }
}
