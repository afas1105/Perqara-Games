package com.example.perqaragames.ui.home

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.perqaragames.base.BaseRecycleViewAdapter
import com.example.perqaragames.base.BaseViewHolder
import com.example.perqaragames.databinding.ItemGamesBinding
import com.example.perqaragames.model.ResultData

class GamesAdapter(val callback: (ResultData) -> Unit ): BaseRecycleViewAdapter<ResultData>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<ResultData> {
        return GamesViewHolder(ItemGamesBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    inner class GamesViewHolder(private val binding: ItemGamesBinding): BaseViewHolder<ResultData>(binding.root) {
        @SuppressLint("SetTextI18n")
        override fun bindData(data: ResultData) {
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