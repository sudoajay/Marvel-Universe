package com.oyelabs.marvel.universe.main.ui.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.oyelabs.marvel.universe.helper.PagingSourceNetwork
import com.oyelabs.marvel.universe.helper.PersonGson
import com.oyelabs.marvel.universe.helper.PicsumInterfaceBuilderGson
import com.oyelabs.marvel.universe.main.api.MarvelApiInterface.Companion.NETWORK_PAGE_SIZE
import com.oyelabs.marvel.universe.main.api.builder.MarvelInterfaceBuilderGson
import com.oyelabs.marvel.universe.main.api.repository.PagingSourceNetworkGson
import com.oyelabs.marvel.universe.main.api.source.dto.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {

    var TAG = "MainViewModelTAG"

//    fun getPagingGsonSourceWithNetwork(): Flow<PagingData<Result>> {
//
//        val apiInterface =
//            MarvelInterfaceBuilderGson.getApiInterface()
//        if(apiInterface == null)
//            Log.e(TAG , "eroor - ")
//        else
//            Log.e(TAG , "Not eroor - ")
//        return Pager(
//            config = PagingConfig(pageSize = NETWORK_PAGE_SIZE, enablePlaceholders = false),
//            pagingSourceFactory = {
//                PagingSourceNetworkGson(
//                    apiInterface!!
//                )
//            }
//        ).flow.cachedIn(viewModelScope)
//    }

    fun getPagingGsonSourceWithNetwork(): Flow<PagingData<PersonGson>> {

        val apiInterface =
            PicsumInterfaceBuilderGson.getApiInterface()


        val flow = Pager(
            // Configure how data is loaded by passing additional properties to
            // PagingConfig, such as prefetchDistance.
            PagingConfig(pageSize = NETWORK_PAGE_SIZE, enablePlaceholders = false)
        ) {
            PagingSourceNetwork(apiInterface!!)
        }.flow
            .cachedIn(viewModelScope)

        val value =  Pager(
            config = PagingConfig(pageSize = NETWORK_PAGE_SIZE, enablePlaceholders = false),
            pagingSourceFactory = {
                PagingSourceNetwork(
                    apiInterface!!
                )
            }
        ).flow
        Log.e(TAG , "valuecheck is passing ")
        return flow
    }


}