package com.oyelabs.marvel.universe.scrolling.ui.repository


import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.oyelabs.marvel.universe.R
import com.oyelabs.marvel.universe.api.pojo.characterComic.ComicResult
import androidx.core.content.ContextCompat.startActivity
import com.oyelabs.marvel.universe.databinding.LayoutPersonListBinding


class ComicViewHolder(
    private val context: Context,
    private val binding: LayoutPersonListBinding
) : RecyclerView.ViewHolder(binding.root) {



    fun bind(result: ComicResult) {

        setImageLoader(result.thumbnail.path + "." + result.thumbnail.extension)
        binding.tvNameCharacter.text = result.title

        binding.tvDescriptionCharacter.text =
            if (result.description.isNullOrBlank()) context.getString(R.string.noDescription_text) else result.description

        binding.materialCardView.setOnClickListener {
            openUrl(result.urls[0].url)
        }

    }


    private fun setImageLoader(url: String) {
        val newUrl = url.replace("http", "https")

        Glide
            .with(context)
            .load(newUrl)
            .placeholder(R.drawable.marvel)
            .error(R.drawable.marvel)
            .into(binding.imgCharacter);


    }


    private fun openUrl(url: String) {

        val sharingIntent = Intent(Intent.ACTION_VIEW)
        sharingIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        sharingIntent.data = Uri.parse(url)

        context.startActivity(sharingIntent)
    }


}