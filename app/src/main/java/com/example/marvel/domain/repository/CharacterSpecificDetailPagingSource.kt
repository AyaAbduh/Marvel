package com.example.marvel.domain.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.game.di.AppModule
import com.example.marvel.data.ItemDetailsCellModel
import com.example.marvel.data.TheDBInterface

class CharacterSpecificDetailPagingSource(val id: String, val requiredDetail: CharacterSpecificDetail): PagingSource<Int, ItemDetailsCellModel>() {
    private  val theDBInterface : TheDBInterface = AppModule.getClient()
    private val characterDetailsRepository = CharacterDetailsRepository(theDBInterface)

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ItemDetailsCellModel> {
        return try {
            val currentPage = params.key ?: 0
            val pageSize = params.loadSize
            val items = characterDetailsRepository.getSpecificDetail(id, requiredDetail, pageSize, currentPage * pageSize)

            LoadResult.Page(
                data = items,
                prevKey = if (currentPage == 0) null else currentPage - 1,
                nextKey = if (items.size < pageSize) null else currentPage + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, ItemDetailsCellModel>): Int? {
        return null
    }
}