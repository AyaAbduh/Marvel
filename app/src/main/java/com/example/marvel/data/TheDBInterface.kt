package com.example.marvel.data

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TheDBInterface {

    //list of characters
    @GET("v1/public/characters")
    suspend fun getCharacters(@Query("ts") ts:String,
                                @Query("apikey") apikey:String,
                                @Query("hash") hash:String): Response<CharacterDataWrapper>

    //character details
    @GET("v1/public/characters/{id}")
    suspend fun getCharacterDetails(@Path("id") id: String,
                                    @Query("ts") ts:String,
                                    @Query("apikey") apikey:String,
                                    @Query("hash") hash:String): Response<CharacterDataWrapper>


    //comics
    @GET("v1/public/characters/{id}/comics")
    suspend fun getComics(@Path("id") id: String,
                                    @Query("ts") ts:String,
                                    @Query("apikey") apikey:String,
                                    @Query("hash") hash:String): Response<ComicDataWrapper>



    //events
    @GET("v1/public/characters/{id}/events")
    suspend fun getEvents(@Path("id") id: String,
                          @Query("ts") ts:String,
                          @Query("apikey") apikey:String,
                          @Query("hash") hash:String): Response<EventDataWrapper>


    //series
    @GET("v1/public/characters/{id}/series")
    suspend fun getSeries(@Path("id") id: String,
                          @Query("ts") ts:String,
                          @Query("apikey") apikey:String,
                          @Query("hash") hash:String): Response<SeriesDataWrapper>


    //stories
    @GET("v1/public/characters/{id}/stories")
    suspend fun getStories(@Path("id") id: String,
                          @Query("ts") ts:String,
                          @Query("apikey") apikey:String,
                          @Query("hash") hash:String): Response<StoryDataWrapper>



}