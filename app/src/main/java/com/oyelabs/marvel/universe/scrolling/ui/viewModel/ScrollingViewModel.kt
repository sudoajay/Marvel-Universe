package com.oyelabs.marvel.universe.scrolling.ui.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
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
class ScrollingViewModel  @Inject constructor(application: Application) : AndroidViewModel(application) {

    var TAG = "MainViewModelTAG"
    var id: Int = 0
    var hideProgress: MutableLiveData<Boolean> = MutableLiveData()
    var noData :Boolean = false
    private var _application = application
    init {
        loadHideProgress()

    }

    private fun loadHideProgress() {
        hideProgress.value = false
    }


    fun getPagingGsonSourceWithNetwork(): Flow<PagingData<ComicResult>> {

        val apiInterface =
            MarvelInterfaceBuilderGson.getApiInterface()
        return Pager(
            config = PagingConfig(pageSize = NETWORK_PAGE_SIZE, enablePlaceholders = false),
            pagingSourceFactory = {
                PagingSourceNetworkGsonCharacterComic(
                    _application,
                    apiInterface!!,
                    id = id
                )
            }
        ).flow.cachedIn(viewModelScope)
    }


}