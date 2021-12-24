package com.oyelabs.marvel.universe.main.api.source.dto

data class Stories(
    val available: Int,
    val collectionURI: String,
    val items: List<ItemXXX>,
    val returned: Int
)