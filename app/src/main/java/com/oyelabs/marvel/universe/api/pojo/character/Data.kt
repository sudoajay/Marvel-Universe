package com.oyelabs.marvel.universe.api.pojo.character

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Data(

    @SerializedName("count")
    val count: Int,
    @SerializedName("limit")
    val limit: Int,
    @SerializedName("offset")
    val offset: Int,
    @SerializedName("results")
    val result: List<CharacterResult>,
    @SerializedName("total")
    val total: Int
): Serializable