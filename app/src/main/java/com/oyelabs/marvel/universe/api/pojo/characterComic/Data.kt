package com.oyelabs.marvel.universe.api.pojo.characterComic

import com.google.gson.annotations.SerializedName
import com.oyelabs.marvel.universe.api.pojo.character.CharacterResult
import java.io.Serializable

data class Data(

    @SerializedName("count")
    val count: Int,
    @SerializedName("limit")
    val limit: Int,
    @SerializedName("offset")
    val offset: Int,
    @SerializedName("results")
    val result: List<ComicResult>,
    @SerializedName("total")
    val total: Int
): Serializable