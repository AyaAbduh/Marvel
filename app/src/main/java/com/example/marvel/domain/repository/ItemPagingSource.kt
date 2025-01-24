package com.example.marvel.domain.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.game.di.AppModule
import com.example.marvel.data.Character
import com.example.marvel.data.TheDBInterface

class ItemPagingSource: PagingSource<Int, Character>() {

    private  val theDBInterface : TheDBInterface = AppModule.getClient()
    private val homeRepository: HomeRepository = HomeRepository(theDBInterface)


    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Character> {
        return try {
            val currentPage = params.key ?: 0
            val pageSize = params.loadSize
            val items = homeRepository.getCharacters("1","32d95cf8e9fabc7cecc342536d6ffaa0","882c430e956a62b3f1e5269ee56015c3",pageSize, currentPage * pageSize)

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