package com.example.perqaragames.ui.home

import android.util.Log
import com.example.perqaragames.base.BasePresenter
import com.example.perqaragames.model.GamesResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomePresenter(view: HomeView): BasePresenter<HomeView>() {

    init {
        attachView(view)
    }

    fun getData(page: Int, pageSize: Int? = 10, search: String){
        val request = retrofitInterface.getListGames(page = page, pageSize = pageSize, search = search)
        request.enqueue(object : Callback<GamesResponse>{
            override fun onResponse(call: Call<GamesResponse>, response: Response<GamesResponse>) {
                if (response.isSuccessful) view?.onGetGames(response.body()?.results)
            }

            override fun onFailure(call: Call<GamesResponse>, t: Throwable) {
                Log.e("afas", t.message.toString())
            }
        })
    }
}