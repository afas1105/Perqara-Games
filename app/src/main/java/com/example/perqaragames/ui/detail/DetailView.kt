package com.example.perqaragames.ui.detail

import com.example.perqaragames.model.GameDetailResponse

interface DetailView {
    fun onGetDetail(data: GameDetailResponse?)
}