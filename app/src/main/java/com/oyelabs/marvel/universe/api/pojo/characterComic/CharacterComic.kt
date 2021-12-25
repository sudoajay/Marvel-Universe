package com.oyelabs.marvel.universe.api.pojo.characterComic

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class CharacterComic(
    @SerializedName("data")
    val data: Data
): Serializable