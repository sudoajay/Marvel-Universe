package com.oyelabs.marvel.universe.main.api.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class MarvelCharacter(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("description") val description: String,
    @SerializedName("thumbnail") val thumbnail: String,
    @SerializedName("thumbnailExt") val thumbnailExt: String,
    @SerializedName("comic") val comic: List<String>
) : Parcelable
