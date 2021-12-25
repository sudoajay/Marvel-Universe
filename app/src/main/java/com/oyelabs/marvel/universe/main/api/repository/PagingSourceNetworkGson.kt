package com.oyelabs.marvel.universe.main.api.repository

import android.content.Context
import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.bumptech.glide.load.HttpException
import com.oyelabs.marvel.universe.R
import com.oyelabs.marvel.universe.helper.Toaster
import com.oyelabs.marvel.universe.main.api.MarvelApiInterface
import com.oyelabs.marvel.universe.main.api.MarvelApiInterface.Companion.STARTING_PAGE_INDEX
import com.oyelabs.marvel.universe.main.api.source.dto.Result
import java.io.IOException

class PagingSourceNetworkGson(
    private val marvelApiInterface: MarvelApiInterface
) : PagingSource<Int , Result>() {
    var TAG = "PagingSourceNetworkGsonTAG"
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int,Result> {
        Log.e(TAG , "Page=  response.data.count ")
        //for first case it will be null, then we can pass some default value, in our case it's 1
        val page = params.key ?: STARTING_PAGE_INDEX

        return try {
            val response = marvelApiInterface.getAllCharacters(offset = page)
            val characters = response.data.results
            Log.e(TAG , "Page= $page response.data.count ${response.data.count} response.data.total ${response.data.total}")
            LoadResult.Page(
                data = characters,
                prevKey = if (page == STARTING_PAGE_INDEX) null else page - 10,
                nextKey = if (response.data.count == response.data.total) null else page + 10
            )

        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }


    override fun getRefreshKey(state: PagingState<Int,Result>): Int? {
        // Try to find the page key of the closest page to anchorPosition, from
        // either the prevKey or the nextKey, but you need to handle nullability
        // here:
        //  * prevKey == null -> anchorPage is the first page.
        //  * nextKey == null -> anchorPage is the last page.
        //  * both prevKey and nextKey null -> anchorPage is the initial page, so
        //    just return null.
        Log.e(TAG , "getRefreshKey ")

        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}