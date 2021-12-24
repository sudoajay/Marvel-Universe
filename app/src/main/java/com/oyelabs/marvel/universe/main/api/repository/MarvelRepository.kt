package com.oyelabs.marvel.universe.main.api.repository

import com.oyelabs.marvel.universe.main.api.source.dto.CharactersList

interface MarvelRepository {
    suspend fun getAllCharacter():CharactersList
}