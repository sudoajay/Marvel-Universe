package com.oyelabs.marvel.universe.main.api.source.dto

import com.oyelabs.marvel.universe.main.api.model.MarvelCharacter

data class Result(
    val comics: Comics,
    val description: String,
    val events: Events,
    val id: Int,
    val modified: String,
    val name: String,
    val resourceURI: String,
    val series: Series,
    val stories: Stories,
    val thumbnail: Thumbnail,
    val urls: List<Url>
){
    fun toCharacter():MarvelCharacter{
        return MarvelCharacter(
            id = id,
            name = name,
            description = description,
            thumbnail =  thumbnail.path,
            thumbnailExt =  thumbnail.extension,
            comic =  comics.items.map {
                it.name
            }
        )
    }


}