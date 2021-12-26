package com.oyelabs.marvel.universe.api.repository

import android.content.Context
import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.bumptech.glide.load.HttpException
import com.oyelabs.marvel.universe.R
import com.oyelabs.marvel.universe.api.MarvelApiInterface
import com.oyelabs.marvel.universe.api.MarvelApiInterface.Companion.STARTING_PAGE_INDEX
import com.oyelabs.marvel.universe.api.pojo.character.CharacterResult
import com.oyelabs.marvel.universe.api.pojo.characterComic.ComicResult
import com.oyelabs.marvel.universe.helper.Toaster
import java.io.IOException

class PagingSourceNetworkGsonCharacterComic(
    private val context: Context,
    private val marvelApiInterface: MarvelApiInterface,
    private val id:Int
) : PagingSource<Int , ComicResult>() {
    var TAG = "PagingSourceNetworkGsonCharacterComicTAG"
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ComicResult> {
        Log.e(TAG , "Page=  response.data.count  id , ${id}" )
        //for first case it will be null, then we can pass some default value, in our case it's 1
        val page = params.key ?: STARTING_PAGE_INDEX

        return try {
            val response = marvelApiInterface.getCharactersComics( characterId = id, offset = page)
            val comics = response.data.result
            Log.e(TAG , "Page= $page  response.data.total ${response.data.total}" +
                    " respone - ")
            LoadResult.Page(
                data = comics,
                prevKey = if (page == STARTING_PAGE_INDEX) null else page - 10,
                nextKey = if (page == response.data.total) null else page +
                        (if (page + 10 <= response.data.total) 10 else response.data.total - page)
            )

        } catch (exception: IOException) {
            Toaster.showToast(context,context.getString(R.string.somethingWentWrong_text))
            return LoadResult.Error(exception)
        } catch (exception: retrofit2.HttpException) {
            Toaster.showToast(context,context.getString(R.string.noInternetConnection_text))
            return LoadResult.Error(exception)
        }
    }


    override fun getRefreshKey(state: PagingState<Int, ComicResult>): Int? {
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