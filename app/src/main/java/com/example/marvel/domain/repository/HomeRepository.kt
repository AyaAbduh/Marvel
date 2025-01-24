package com.example.marvel.domain.repository

import android.util.Log
import com.example.marvel.data.Character
import com.example.marvel.data.CharacterDataWrapper
import com.example.marvel.data.ComicDataWrapper
import com.example.marvel.data.EventDataWrapper
import com.example.marvel.data.SeriesDataWrapper
import com.example.marvel.data.StoryDataWrapper
import com.example.marvel.data.TheDBInterface
import retrofit2.Response

class HomeRepository (private  val theDBInterface: TheDBInterface){


    suspend fun getCharacters(ts:String,key:String,hash :String,limit:Int,offset:Int ): List<Character> {
        val response = theDBInterface.getCharacters(ts,key,hash,limit,offset)
        if(response.isSuccessful){
            Log.e("successs","sucessss")
        }else{
            Log.e("error","error")
        }
        return response.body()?.data?.characterList!!
    }

    suspend fun getCharacterDetails(id:String,ts:String,key:String,hash :String ): Response<CharacterDataWrapper> {
        val response = theDBInterface.getCharacterDetails(id,ts,key,hash)
        if(response.isSuccessful){
            Log.e("successs","sucessss")
        }else{
            Log.e("error","error")
        }
        return response
    }


    suspend fun getComics(id:String,ts:String,key:String,hash :String ): Response<ComicDataWrapper> {
        val response = theDBInterface.getComics(id,ts,key,hash)
        if(response.isSuccessful){
            Log.e("successs","sucessss")
        }else{
            Log.e("error","error")
        }
        return response
    }

    suspend fun getEvents(id:String,ts:String,key:String,hash :String ): Response<EventDataWrapper> {
        val response = theDBInterface.getEvents(id,ts,key,hash)
        if(response.isSuccessful){
            Log.e("successs","sucessss")
        }else{
            Log.e("error","error")
        }
        return response
    }

    suspend fun getSeries(id:String,ts:String,key:String,hash :String ): Response<SeriesDataWrapper> {
        val response = theDBInterface.getSeries(id,ts,key,hash)
        if(response.isSuccessful){
            Log.e("successs","sucessss")
        }else{
            Log.e("error","error")
        }
        return response
    }

    suspend fun getStories(id:String,ts:String,key:String,hash :String ): Response<StoryDataWrapper> {
        val response = theDBInterface.getStories(id,ts,key,hash)
        if(response.isSuccessful){
            Log.e("successs","sucessss")
        }else{
            Log.e("error","error")
        }
        return response
    }


}