package com.oyelabs.marvel.universe.main.api.source.dto

data class Events(
    val available: Int,
    val collectionURI: String,
    val items: List<ItemX>,
    val returned: Int
)