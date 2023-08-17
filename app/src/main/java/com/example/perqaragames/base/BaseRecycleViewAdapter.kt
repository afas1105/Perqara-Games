package com.example.perqaragames.base

import android.annotation.SuppressLint
import androidx.recyclerview.widget.RecyclerView

abstract class BaseRecycleViewAdapter<T> : RecyclerView.Adapter<BaseViewHolder<T>>() {

    private var data: MutableList<T> = mutableListOf()
    private var lastPosition = 0


    @SuppressLint("NotifyDataSetChanged")
    open fun setData(data: List<T>) {
        this.data = data as MutableList<T>
        notifyDataSetChanged()
    }

    fun removeAllData() {
        this.data.clear()
        notifyItemRangeRemoved(0, data.size)
    }

    fun getData(): MutableList<T> {
        return this.data
    }

    private fun getItem(position: Int): T {
        return data[position]
    }


    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: BaseViewHolder<T>, position: Int) {
        holder.bindData(getItem(position))
    }
}