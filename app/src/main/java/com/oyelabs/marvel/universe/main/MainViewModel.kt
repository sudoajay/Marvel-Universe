package com.oyelabs.marvel.universe.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {
    var searchValue = ""

    var hideProgress: MutableLiveData<Boolean> = MutableLiveData()

    init {
        loadHideProgress()

    }

    private fun loadHideProgress() {
        hideProgress.value = false
    }
}