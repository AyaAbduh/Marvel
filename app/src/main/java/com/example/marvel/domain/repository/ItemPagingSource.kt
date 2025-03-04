package com.example.marvel.domain.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.game.di.AppModule
import com.example.marvel.data.Character
import com.example.marvel.data.TheDBInterface

class ItemPagingSource(val searchQuery: String = ""): PagingSource<Int, Character>() {

    private  val theDBInterface : TheDBInterface = AppModule.getClient()
    private val homeRepository = HomeRepository(theDBInterface)
    private val searchRepository = SearchRepository(theDBInterface)

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Character> {
        return try {
            val currentPage = params.key ?: 0
            val pageSize = params.loadSize
            val items = if (searchQuery.isEmpty()) {
                homeRepository.getCharacters(pageSize, currentPage * pageSize)
            } else {
                searchRepository.searchFor(searchQuery, pageSize, currentPage * pageSize)
            }

            LoadResult.Page(
                data = items,
                prevKey = if (currentPage == 0) null else currentPage - 1,
                nextKey = if (items.size < pageSize) null else currentPage + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Character>): Int? {
        return null
    }
}