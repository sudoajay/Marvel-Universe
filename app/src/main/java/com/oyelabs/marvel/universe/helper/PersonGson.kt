package com.oyelabs.marvel.universe.helper

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class PersonGson(
    @SerializedName("id") val id: Int,
    @SerializedName("author")val name: String,
    @SerializedName("width")val width: Int,
    @SerializedName("height")val height: Int,
    @SerializedName("url")val openUrl: String,
    @SerializedName("download_url")val downloadUrl: String,
): Parcelable

