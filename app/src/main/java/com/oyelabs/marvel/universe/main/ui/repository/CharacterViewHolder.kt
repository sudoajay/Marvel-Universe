package com.oyelabs.marvel.universe.main.ui.repository


import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.oyelabs.marvel.universe.R
import com.oyelabs.marvel.universe.api.pojo.character.CharacterResult
import com.oyelabs.marvel.universe.api.pojo.characterComic.ComicResult
import com.oyelabs.marvel.universe.databinding.LayoutPersonGridListBinding
import com.oyelabs.marvel.universe.scrolling.ScrollingActivity


class CharacterViewHolder(
    private val context: Context,
    private val binding: LayoutPersonGridListBinding
) : RecyclerView.ViewHolder(binding.root) {

    val TAG = "PersonViewHolderTAG"

    fun bind(result:CharacterResult) {
        setImageLoader(result.thumbnail.path + "." + result.thumbnail.extension)
        binding.tvNameCharacter.text = result.name

        binding.tvDescriptionCharacter.text =
            if (result.description.isBlank()) context.getString(R.string.noDescription_text) else result.description.limitDescription(
                100
            )

        binding.imgCharacter.setOnClickListener {

            showMoreAboutCharacter(result)
        }

    }


    private fun setImageLoader(url: String) {
        val newUrl = url.replace("http", "https")
        Log.e(TAG, "newUrl - ${newUrl}")
        Glide
            .with(context)
            .load(newUrl)
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



    private fun showMoreAboutCharacter(result: CharacterResult) {

        val intent = Intent(context, ScrollingActivity::class.java)
        intent.putExtra("id", result.id.toString())
        intent.putExtra("name", result.name)
        intent.putExtra("imageUrl", (result.thumbnail.path + "." + result.thumbnail.extension).replace("http", "https"))
        intent.putExtra("url", result.urls[0].url  )
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        context.startActivity(intent)
    }



}