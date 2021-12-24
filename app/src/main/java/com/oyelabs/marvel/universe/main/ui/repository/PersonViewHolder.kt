package com.oyelabs.marvel.universe.main.ui.repository


import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.oyelabs.marvel.universe.R
import com.oyelabs.marvel.universe.databinding.LayoutPersonGridListBinding
import com.oyelabs.marvel.universe.main.MainActivity
import com.oyelabs.marvel.universe.main.api.model.MarvelCharacter


class PersonViewHolder(
    private val mainActivity: MainActivity,
    private val binding: LayoutPersonGridListBinding
) : RecyclerView.ViewHolder(binding.root) {


    fun bind(marvelCharacter: MarvelCharacter ) {
        setImageLoader(marvelCharacter.thumbnail)

        binding.personTextView.text = marvelCharacter.name

    }
    

    private fun setImageLoader(url: String) {

        Glide
            .with(mainActivity)
            .load(url)
            .centerCrop()
            .override(120, 120)
            .placeholder(R.drawable.app_icon)
            .error(R.drawable.app_icon)
            .into(binding.personImageView);


    }


}