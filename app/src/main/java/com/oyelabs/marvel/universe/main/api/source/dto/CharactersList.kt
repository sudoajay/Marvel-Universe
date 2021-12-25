package com.oyelabs.marvel.universe.main.api.source.dto


data class CharactersList(
    val attributionHTML: String,
    val attributionText: String,
    val code: Int,
    val copyright: String,
    val `data`: Data,
    val etag: String,
    val status: String
)