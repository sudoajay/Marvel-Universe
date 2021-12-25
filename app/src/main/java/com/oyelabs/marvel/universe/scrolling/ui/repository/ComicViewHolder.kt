package com.oyelabs.marvel.universe.scrolling.ui.repository


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


class ComicViewHolder(
    private val context: Context,
    private val binding: LayoutPersonGridListBinding
) : RecyclerView.ViewHolder(binding.root) {

    val TAG = "ComicViewHolderTAG"

    fun bind(result:ComicResult) {
        Log.e(TAG , "Value id - ${result.id}   value title - ${result.title} " +
                "value Thumbainal ${(result.thumbnail.path + "." + result.thumbnail.extension).replace("http", "https")}")
        setImageLoader(result.thumbnail.path + "." + result.thumbnail.extension)
        binding.tvNameCharacter.text = result.title

        binding.tvDescriptionCharacter.text =
            if (result.description.isBlank()) context.getString(R.string.noDescription_text) else result.description

        binding.imgCharacter.setOnClickListener {
            openUrl(result.urls[0].url)
        }

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




    private fun openUrl(url:String) {
        context.startActivity(
            Intent(
                Intent.ACTION_VIEW,
                Uri.parse(url)
            )
        )
    }


}