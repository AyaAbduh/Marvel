package com.example.marvel.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.game.di.AppModule
import com.example.marvel.data.CharacterDataWrapper
import com.example.marvel.data.ComicDataWrapper
import com.example.marvel.data.EventDataWrapper
import com.example.marvel.data.SeriesDataWrapper
import com.example.marvel.data.StoryDataWrapper
import com.example.marvel.data.TheDBInterface
import com.example.marvel.domain.repository.HomeRepository
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    private  val theDBInterface : TheDBInterface = AppModule.getClient()
    private val homeRepository: HomeRepository = HomeRepository(theDBInterface)

    private val _getCharacters= MutableLiveData<CharacterDataWrapper>()
    val getCharactersLiveData: LiveData<CharacterDataWrapper> = _getCharacters

    private val _getComics= MutableLiveData<ComicDataWrapper>()
    val getComicsLiveData: LiveData<ComicDataWrapper> = _getComics

    private val _getEvents= MutableLiveData<EventDataWrapper>()
    val getEventsLiveData: LiveData<EventDataWrapper> = _getEvents

    private val _getSeries= MutableLiveData<SeriesDataWrapper>()
    val getSeriesLiveData: LiveData<SeriesDataWrapper> = _getSeries

    private val _getStories= MutableLiveData<StoryDataWrapper>()
    val getStoriesLiveData: LiveData<StoryDataWrapper> = _getStories


    fun getCharacters(ts:String,key:String,hash :String){
        viewModelScope.launch {
            val response =homeRepository.getCharacters(ts,key,hash)
            _getCharacters.postValue(response.body())
        }

    }


    fun getCharacterDetails(id:String,ts:String,key:String,hash :String){
        viewModelScope.launch {
            val response =homeRepository.getCharacterDetails(id,ts,key,hash)
            _getCharacters.postValue(response.body())
        }

    }

    fun getComics(id:String,ts:String,key:String,hash :String){
        viewModelScope.launch {
            val response =homeRepository.getComics(id,ts,key,hash)
            _getComics.postValue(response.body())
        }

    }

    fun getEvents(id:String,ts:String,key:String,hash :String){
        viewModelScope.launch {
            val response =homeRepository.getEvents(id,ts,key,hash)
            _getEvents.postValue(response.body())
        }

    }

    fun getSeries(id:String,ts:String,key:String,hash :String){
        viewModelScope.launch {
            val response =homeRepository.getSeries(id,ts,key,hash)
            _getSeries.postValue(response.body())
        }

    }

    fun getStories(id:String,ts:String,key:String,hash :String){
        viewModelScope.launch {
            val response =homeRepository.getStories(id,ts,key,hash)
            _getStories.postValue(response.body())
        }

    }





}