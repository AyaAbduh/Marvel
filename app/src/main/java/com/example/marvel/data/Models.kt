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

data class ComicDataWrapper(
    val code: Int?,
    @SerializedName("data")
    val data: ComicDataContainer?
)
data class ComicDataContainer(
    val count: Int?,
    @SerializedName("results")
    val ComicsList: List<Comic>?
)
data class Comic(
    val id: Int?,
    val title: String?,
    @SerializedName("thumbnail")
    val thumbnail:com.example.marvel.data.Image?
)

data class EventDataWrapper(
    val code: Int?,
    @SerializedName("data")
    val data: EventDataContainer?
)

data class EventDataContainer(
    val count: Int?,
    @SerializedName("results")
    val eventList: List<Event>?
)
data class Event(
    val id: Int?,
    val title: String?,
    @SerializedName("thumbnail")
    val thumbnail:com.example.marvel.data.Image?
)
data class SeriesDataWrapper(
    val code: Int?,
    @SerializedName("data")
    val data: SeriesDataContainer?
)

data class SeriesDataContainer(
    val count: Int?,
    @SerializedName("results")
    val seriesList: List<Series>?
)

data class Series(
    val id: Int?,
    val title: String?,
    @SerializedName("thumbnail")
    val thumbnail:com.example.marvel.data.Image?
)
data class StoryDataWrapper(
    val code: Int?,
    @SerializedName("data")
    val data: StoryDataContainer?
)

data class StoryDataContainer(
    val count: Int?,
    @SerializedName("results")
    val storyList: List<Story>?
)

data class Story(
    val id: Int?,
    val title: String?,
    @SerializedName("thumbnail")
    val thumbnail:com.example.marvel.data.Image?
)