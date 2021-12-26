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
import com.oyelabs.marvel.universe.databinding.LayoutPersonGridListBinding
import androidx.core.content.ContextCompat.startActivity





class ComicViewHolder(
    private val context: Context,
    private val binding: LayoutPersonGridListBinding
) : RecyclerView.ViewHolder(binding.root) {

    val TAG = "ComicViewHolderTAG"

    fun bind(result: ComicResult) {
        Log.e(
            TAG, "Value id - ${result.id}   value title - ${result.title} " +
                    "value Thumbainal ${
                        (result.thumbnail.path + "." + result.thumbnail.extension).replace(
                            "http",
                            "https"
                        )
                    } +   description = ${result.description}"
        )
        setImageLoader(result.thumbnail.path + "." + result.thumbnail.extension)
        binding.tvNameCharacter.text = result.title

        binding.tvDescriptionCharacter.text =
            if (result.description.isNullOrBlank()) context.getString(R.string.noDescription_text) else result.description

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