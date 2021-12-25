package com.oyelabs.marvel.universe.scrolling.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.oyelabs.marvel.universe.api.MarvelApiInterface.Companion.NETWORK_PAGE_SIZE
import com.oyelabs.marvel.universe.api.builder.MarvelInterfaceBuilderGson
import com.oyelabs.marvel.universe.api.pojo.character.CharacterResult
import com.oyelabs.marvel.universe.api.pojo.characterComic.ComicResult
import com.oyelabs.marvel.universe.api.repository.PagingSourceNetworkGsonCharacterComic
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class ScrollingViewModel @Inject constructor() : ViewModel() {

    var TAG = "MainViewModelTAG"
    var id: Int = 0


    fun getPagingGsonSourceWithNetwork(): Flow<PagingData<ComicResult>> {

        val apiInterface =
            MarvelInterfaceBuilderGson.getApiInterface()
        return Pager(
            config = PagingConfig(pageSize = NETWORK_PAGE_SIZE, enablePlaceholders = false),
            pagingSourceFactory = {
                PagingSourceNetworkGsonCharacterComic(
                    apiInterface!!,
                    id = id
                )
            }
        ).flow.cachedIn(viewModelScope)
    }


}