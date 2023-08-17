package com.example.perqaragames.ui.favorite

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.perqaragames.base.BaseRecycleViewAdapter
import com.example.perqaragames.base.BaseViewHolder
import com.example.perqaragames.databinding.ItemGamesBinding
import com.example.perqaragames.local.FavoriteData

class FavoriteAdapter(val callback: (FavoriteData) -> Unit ): BaseRecycleViewAdapter<FavoriteData>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<FavoriteData> {
        return FavoriteViewHolder(ItemGamesBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    inner class FavoriteViewHolder(private val binding: ItemGamesBinding): BaseViewHolder<FavoriteData>(binding.root) {
        @SuppressLint("SetTextI18n")
        override fun bindData(data: FavoriteData) {
            Log.e("afas", "in Adapter : $data")
            binding.apply {
                Glide.with(root).load(data.backgroundImage).into(ivGame)
                tvNameGame.text = data.name
                tvReleased.text = "Released date ${data.released}"
                tvRating.text = data.rating.toString()
                root.setOnClickListener {
                    callback(data)
                }
            }
        }
    }
}