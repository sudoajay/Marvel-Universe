package com.oyelabs.marvel.universe.main.ui.viewModel

import android.app.Application
import android.content.Context
import android.util.Log
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
import com.oyelabs.marvel.universe.api.repository.PagingSourceNetworkGsonCharacter
import com.oyelabs.marvel.universe.api.pojo.character.CharacterResult
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(application: Application) : AndroidViewModel(application) {

    var TAG = "MainViewModelTAG"
    var hideProgress: MutableLiveData<Boolean> = MutableLiveData()
    var noData :Boolean = false
    var search : String? = null
    private var _application = application

    init {
        loadHideProgress()

    }

    private fun loadHideProgress() {
        hideProgress.value = false
    }
    fun getPagingGsonSourceWithNetwork(): Flow<PagingData<CharacterResult>> {
        if (search.isNullOrBlank()) search = null
        val apiInterface =
            MarvelInterfaceBuilderGson.getApiInterface()
        return Pager(
            config = PagingConfig(pageSize = NETWORK_PAGE_SIZE, enablePlaceholders = false),
            pagingSourceFactory = {
                PagingSourceNetworkGsonCharacter(
                    context = _application,
                    apiInterface!!,
                    search
                )
            }
        ).flow.cachedIn(viewModelScope)
    }




}