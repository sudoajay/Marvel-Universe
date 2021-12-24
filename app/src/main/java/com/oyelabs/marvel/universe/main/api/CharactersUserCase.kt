package com.oyelabs.marvel.universe.main.api

import com.oyelabs.marvel.universe.main.api.model.MarvelCharacter
import com.oyelabs.marvel.universe.main.api.repository.MarvelRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class CharactersUserCase(private val repository: MarvelRepository) {

    operator fun invoke(): Flow<Response<List<MarvelCharacter>>> = flow {
        try {
            emit(Response.Loading())
            val list = repository.getAllCharacter().data.results.map {  it.toCharacter()}
            emit(Response.Success(list))
        } catch (exc: Exception) {
            emit(Response.Error(message = exc.printStackTrace().toString() + "Some error while Fetching ") )
        }

    }
}