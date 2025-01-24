package com.example.marvel.data

import android.media.Image
import android.media.ImageReader
import com.google.gson.annotations.SerializedName

//Characters
data class CharacterDataWrapper(
    val code: Int?,
    @SerializedName("data")
    val data: CharacterDataContainer?
)

data class CharacterDataContainer(
    val count: Int?,
    @SerializedName("results")
    val characterList: List<Character>?
)

data class Character(
    val id: Int?,
    val name: String?,
    val description: String?,
    val modified:String?,
    @SerializedName("thumbnail")
    val thumbnail:com.example.marvel.data.Image?
)

data class Image(
    val path:String?,
    val extension:String?
)

data class DataWrapper(
    val code: Int?,
    @SerializedName("data")
    val data: DataContainer?
)

data class DataContainer(
    val count: Int?,
    @SerializedName("results")
    val dataList: List<ItemDetailsCellModel>?
)

data class ItemDetailsCellModel(
    val id: Int?,
    val title: String?,
    @SerializedName("thumbnail")
    val thumbnail:com.example.marvel.data.Image?
)
