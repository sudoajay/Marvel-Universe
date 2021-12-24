package com.oyelabs.marvel.universe.main.ui.repository

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.oyelabs.marvel.universe.databinding.LayoutPersonGridListBinding
import com.oyelabs.marvel.universe.main.MainActivity
import com.oyelabs.marvel.universe.main.api.model.MarvelCharacter
import javax.inject.Inject


class PersonPagingAdapterGson @Inject constructor(
) :
    PagingDataAdapter<MarvelCharacter, PersonViewHolder>(Person_COMPARATOR) {
    lateinit var mainActivity: MainActivity


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        PersonViewHolder(
            mainActivity,
            LayoutPersonGridListBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )

    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }



    companion object {
        private val Person_COMPARATOR = object : DiffUtil.ItemCallback<MarvelCharacter>() {
            override fun areItemsTheSame(oldItem: MarvelCharacter, newItem: MarvelCharacter): Boolean =
                oldItem == newItem

            override fun areContentsTheSame(oldItem: MarvelCharacter, newItem: MarvelCharacter): Boolean =
                oldItem == newItem
        }
    }


}