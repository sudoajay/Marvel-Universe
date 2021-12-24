package com.oyelabs.marvel.universe.main.api.source.dto

data class Comics(
    val available: Int,
    val collectionURI: String,
    val items: List<Item>,
    val returned: Int
)