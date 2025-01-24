package com.example.marvel.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.liveData
import com.example.marvel.data.Character
import com.example.marvel.domain.repository.ItemPagingSource

class SearchViewModel: ViewModel() {

    fun searchFor(searchQuery: String): LiveData<PagingData<Character>> {
        print("Searching $searchQuery")
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { ItemPagingSource(searchQuery) }
        ).liveData.cachedIn(viewModelScope)
    }
}