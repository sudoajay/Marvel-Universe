package com.oyelabs.marvel.universe.scrolling.model

import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class CharacterInfo(
    val id: Int,
    val name: String,
    val thumbnail: String,
    val url: String
)


