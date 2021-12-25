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

    fun bind(result: Result ) {
        setImageLoader(result.thumbnail.path+"."+result.thumbnail.extension)
        binding.personTextView.text = result.name
    }
    

    private fun setImageLoader(url: String) {
        val newUrl = url.replace("http","https")
        Log.e(TAG , "newUrl - ${newUrl}")
        Glide
            .with(context)
            .load(newUrl)
            .centerCrop()
            .override(80, 80)
            .placeholder(R.drawable.app_icon)
            .error(R.drawable.app_icon)
            .into(binding.personImageView);


    }


}