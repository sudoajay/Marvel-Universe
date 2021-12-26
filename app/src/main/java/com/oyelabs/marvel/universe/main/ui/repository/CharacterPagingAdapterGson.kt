package com.oyelabs.marvel.universe.main.ui.repository

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.oyelabs.marvel.universe.databinding.LayoutPersonGridListBinding
import com.oyelabs.marvel.universe.api.pojo.character.CharacterResult
import javax.inject.Inject


class CharacterPagingAdapterGson @Inject constructor(var context: Context
) :
    PagingDataAdapter<CharacterResult, CharacterViewHolder>(Person_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        CharacterViewHolder(
            context,
            LayoutPersonGridListBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }



    companion object {
        private val Person_COMPARATOR = object : DiffUtil.ItemCallback<CharacterResult>() {
            override fun areItemsTheSame(oldItem: CharacterResult, newItem: CharacterResult): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: CharacterResult, newItem: CharacterResult): Boolean =
                oldItem == newItem
        }
    }


}