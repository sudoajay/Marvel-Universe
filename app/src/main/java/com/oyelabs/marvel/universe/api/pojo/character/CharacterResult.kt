package com.oyelabs.marvel.universe.api.pojo.character

import com.google.gson.annotations.SerializedName
import com.oyelabs.marvel.universe.api.pojo.Thumbnail
import com.oyelabs.marvel.universe.api.pojo.Url
import java.io.Serializable

data class CharacterResult(

    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("thumbnail")
    val thumbnail: Thumbnail,
    @SerializedName("urls")
    val urls: List<Url>
): Serializable


