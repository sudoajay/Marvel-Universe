package com.oyelabs.marvel.universe.main.api.source.dto

data class Series(
    val available: Int,
    val collectionURI: String,
    val items: List<ItemXX>,
    val returned: Int
)