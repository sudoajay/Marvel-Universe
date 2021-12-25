package com.oyelabs.marvel.universe.scrolling.model

import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class CharacterInfo(
    val id: Long,
    val name: String,
    val thumbnail: String,
    val url: String
)


