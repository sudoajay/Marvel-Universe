package com.oyelabs.marvel.universe.main.ui.repository


import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.oyelabs.marvel.universe.R
import com.oyelabs.marvel.universe.databinding.LayoutPersonGridListBinding
import com.oyelabs.marvel.universe.main.api.source.dto.Result


class PersonViewHolder(
    private val context: Context,
    private val binding: LayoutPersonGridListBinding
) : RecyclerView.ViewHolder(binding.root) {


    fun bind(result: Result ) {
        setImageLoader(result.thumbnail.path+result.thumbnail.extension)

        binding.personTextView.text = result.name

    }
    

    private fun setImageLoader(url: String) {

        Glide
            .with(context)
            .load(url)
            .centerCrop()
            .override(120, 120)
            .placeholder(R.drawable.app_icon)
            .error(R.drawable.app_icon)
            .into(binding.personImageView);


    }


}