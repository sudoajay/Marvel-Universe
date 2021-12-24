package com.oyelabs.marvel.universe.main.api.source.repository

import com.oyelabs.marvel.universe.main.api.MarvelApiInterface
import com.oyelabs.marvel.universe.main.api.repository.MarvelRepository
import com.oyelabs.marvel.universe.main.api.source.dto.CharactersList

class MarvelRepositoryImpl (private val api:MarvelApiInterface):MarvelRepository {
    override suspend fun getAllCharacter(): CharactersList {
        return api.getAllCharacters()
    }

}