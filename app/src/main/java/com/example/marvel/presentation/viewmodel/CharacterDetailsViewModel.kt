package com.example.marvel.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.game.di.AppModule
import com.example.marvel.data.CharacterDataWrapper
import com.example.marvel.data.DataWrapper
import com.example.marvel.data.TheDBInterface
import com.example.marvel.domain.repository.HomeRepository
import kotlinx.coroutines.launch

class CharacterDetailsViewModel: ViewModel() {
    private  val theDBInterface : TheDBInterface = AppModule.getClient()
    private val homeRepository: HomeRepository = HomeRepository(theDBInterface)

    private val _getCharacters= MutableLiveData<CharacterDataWrapper>()
    val getCharactersLiveData: LiveData<CharacterDataWrapper> = _getCharacters

    private val _getComics= MutableLiveData<DataWrapper>()
    val getComicsLiveData: LiveData<DataWrapper> = _getComics

    private val _getEvents= MutableLiveData<DataWrapper>()
    val getEventsLiveData: LiveData<DataWrapper> = _getEvents

    private val _getSeries= MutableLiveData<DataWrapper>()
    val getSeriesLiveData: LiveData<DataWrapper> = _getSeries

    private val _getStories= MutableLiveData<DataWrapper>()
    val getStoriesLiveData: LiveData<DataWrapper> = _getStories

    fun getCharacterDetails(id:String) {
        viewModelScope.launch {
            val response = homeRepository.getCharacterDetails(id)
            _getCharacters.postValue(response.body())
        }
    }

    fun getComics(id:String) {
        viewModelScope.launch {
            val response = homeRepository.getComics(id)
            _getComics.postValue(response.body())
        }
    }

    fun getEvents(id:String) {
        viewModelScope.launch {
            val response = homeRepository.getEvents(id)
            _getEvents.postValue(response.body())
        }
    }

    fun getSeries(id:String) {
        viewModelScope.launch {
            val response = homeRepository.getSeries(id)
            _getSeries.postValue(response.body())
        }
    }

    fun getStories(id:String) {
        viewModelScope.launch {
            val response =homeRepository.getStories(id)
            _getStories.postValue(response.body())
        }
    }
}