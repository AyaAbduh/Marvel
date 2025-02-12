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
import com.example.marvel.domain.repository.ItemPagingSource
import kotlinx.coroutines.flow.Flow

class HomeViewModel: ViewModel() {

    val items: Flow<PagingData<Character>> = Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { ItemPagingSource() }
        ).flow.cachedIn(viewModelScope)
}