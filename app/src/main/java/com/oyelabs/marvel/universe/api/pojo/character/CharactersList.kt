package com.oyelabs.marvel.universe.api.pojo.character

import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class CharactersList(
    @SerializedName("data")
    val data: Data
): Serializable