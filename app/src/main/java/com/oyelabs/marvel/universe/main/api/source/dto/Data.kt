package com.oyelabs.marvel.universe.main.api.source.dto

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
    val result: List<Result>,
    @SerializedName("total")
    val total: Int
): Serializable