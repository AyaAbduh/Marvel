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
import com.example.marvel.data.Character
import com.example.marvel.data.CharacterDataWrapper
import com.example.marvel.domain.repository.ItemPagingSource

class HomeViewModel : ViewModel() {

    private val _getCharacters= MutableLiveData<CharacterDataWrapper>()
    val getCharactersLiveData: LiveData<CharacterDataWrapper> = _getCharacters
    val items: LiveData<PagingData<Character>> = Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { ItemPagingSource() }
        ).liveData.cachedIn(viewModelScope)
}