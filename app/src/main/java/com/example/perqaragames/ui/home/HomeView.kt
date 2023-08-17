package com.example.perqaragames.ui.home

import com.example.perqaragames.model.ResultData

interface HomeView {
    fun onGetGames(data: List<ResultData>?)
}