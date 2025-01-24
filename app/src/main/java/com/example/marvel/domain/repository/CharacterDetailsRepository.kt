package com.example.marvel.domain.repository

import android.util.Log
import com.example.marvel.R
import com.example.marvel.data.Character
import com.example.marvel.data.CharacterDataWrapper
import com.example.marvel.data.DataWrapper
import com.example.marvel.data.ItemDetailsCellModel
import com.example.marvel.data.TheDBInterface
import retrofit2.Response

class CharacterDetailsRepository(private val theDBInterface: TheDBInterface) {
    private val ts = "1"
    private val publicKey = "32d95cf8e9fabc7cecc342536d6ffaa0"
    private val hash = "882c430e956a62b3f1e5269ee56015c3"

    suspend fun getCharacterDetails(id: String): Response<CharacterDataWrapper> {
        val response = theDBInterface.getCharacterDetails(id, ts, publicKey, hash)
        if (response.isSuccessful) {
            Log.e("successs", "sucessss")
        } else {
            Log.e("error", "error")
        }
        return response
    }

    suspend fun getSpecificDetail(id: String, requiredDetails: CharacterSpecificDetail, limit: Int, offset: Int): List<ItemDetailsCellModel> {
        val response = when(requiredDetails) {
            CharacterSpecificDetail.series -> theDBInterface.getSeries(id, ts, publicKey, hash, limit, offset)
            CharacterSpecificDetail.comics -> theDBInterface.getComics(id, ts, publicKey, hash, limit, offset)
            CharacterSpecificDetail.stories -> theDBInterface.getStories(id, ts, publicKey, hash, limit, offset)
            CharacterSpecificDetail.events -> theDBInterface.getEvents(id, ts, publicKey, hash, limit, offset)
        }
        if (response.isSuccessful) {
            Log.e("successs", "sucessss")
        } else {
            Log.e("error", "error")
        }
        return response.body()?.data?.dataList!!
    }
}