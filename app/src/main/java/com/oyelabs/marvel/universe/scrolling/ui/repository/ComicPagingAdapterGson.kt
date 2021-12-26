package com.oyelabs.marvel.universe.scrolling.ui.repository

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.oyelabs.marvel.universe.databinding.LayoutPersonGridListBinding
import com.oyelabs.marvel.universe.api.pojo.character.CharacterResult
import com.oyelabs.marvel.universe.api.pojo.characterComic.ComicResult
import com.oyelabs.marvel.universe.main.ui.repository.CharacterViewHolder
import javax.inject.Inject


class ComicPagingAdapterGson @Inject constructor(var context: Context
) :
    PagingDataAdapter<ComicResult, ComicViewHolder>(Person_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ComicViewHolder(
            context,
            LayoutPersonGridListBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )

    override fun onBindViewHolder(holder: ComicViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    companion object {
        private val Person_COMPARATOR = object : DiffUtil.ItemCallback<ComicResult>() {
            override fun areItemsTheSame(oldItem: ComicResult, newItem: ComicResult): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: ComicResult, newItem: ComicResult): Boolean =
                oldItem == newItem
        }
    }


}