package com.example.perqaragames.base

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder<T>(view: View) : RecyclerView.ViewHolder(view) {
    abstract fun bindData(data: T)
}