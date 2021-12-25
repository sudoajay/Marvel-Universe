package com.oyelabs.marvel.universe.main.ui.repository


import android.content.Context
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.oyelabs.marvel.universe.R
import com.oyelabs.marvel.universe.databinding.LayoutPersonGridListBinding
import com.oyelabs.marvel.universe.main.api.source.dto.Result


class PersonViewHolder(
    private val context: Context,
    private val binding: LayoutPersonGridListBinding
) : RecyclerView.ViewHolder(binding.root) {

    val TAG = "PersonViewHolderTAG"

    fun bind(result: Result) {
        setImageLoader(result.thumbnail.path + "." + result.thumbnail.extension)
        binding.tvNameCharacter.text = result.name

        binding.tvDescriptionCharacter.text =
            if (result.description.isBlank()) context.getString(R.string.noDescription_text) else result.description.limitDescription(
                100
            )
    }


    private fun setImageLoader(url: String) {
        val newUrl = url.replace("http", "https")
        Log.e(TAG, "newUrl - ${newUrl}")
        Glide
            .with(context)
            .load(newUrl)
            .override(200, 200)
            .placeholder(R.drawable.marvel)
            .error(R.drawable.marvel)
            .into(binding.imgCharacter);


    }

    private fun String.limitDescription(characters: Int): String {
        if (this.length > characters) {
            val firstCharacter = 0
            return "${this.substring(firstCharacter, characters)}..."
        }
        return this
    }


}