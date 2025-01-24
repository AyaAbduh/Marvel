package com.example.marvel.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.liveData
import com.example.game.di.AppModule
import com.example.marvel.data.CharacterDataWrapper
import com.example.marvel.data.ItemDetailsCellModel
import com.example.marvel.data.TheDBInterface
import com.example.marvel.domain.repository.CharacterDetailsRepository
import com.example.marvel.domain.repository.CharacterSpecificDetail
import com.example.marvel.domain.repository.CharacterSpecificDetailPagingSource
import kotlinx.coroutines.launch

class CharacterDetailsViewModel: ViewModel() {
    private  val theDBInterface : TheDBInterface = AppModule.getClient()
    private val characterDetailsRepository = CharacterDetailsRepository(theDBInterface)

    private val _getCharacters= MutableLiveData<CharacterDataWrapper>()
    val getCharactersLiveData: LiveData<CharacterDataWrapper> = _getCharacters

    fun getCharacterDetails(id:String) {
        viewModelScope.launch {
            val response = characterDetailsRepository.getCharacterDetails(id)
            _getCharacters.postValue(response.body())
        }
    }

    fun getSpecificDetail(id: String, characterSpecificDetails: CharacterSpecificDetail): LiveData<PagingData<ItemDetailsCellModel>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { CharacterSpecificDetailPagingSource(id, characterSpecificDetails) }
        ).liveData.cachedIn(viewModelScope)
    }
}