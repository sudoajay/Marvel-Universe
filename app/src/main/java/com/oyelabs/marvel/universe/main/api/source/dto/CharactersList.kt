package com.oyelabs.marvel.universe.main.api.source.dto

import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class CharactersList(
    @SerializedName("data")
    val data: Data
): Serializable