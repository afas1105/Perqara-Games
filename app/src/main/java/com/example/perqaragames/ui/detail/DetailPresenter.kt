package com.example.perqaragames.ui.detail

import android.util.Log
import com.example.perqaragames.base.BasePresenter
import com.example.perqaragames.model.GameDetailResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailPresenter(view: DetailView): BasePresenter<DetailView>() {

    init {
        attachView(view)
    }

    fun getDetail(id: Int) {
        val request = retrofitInterface.getDetailGames(id = id)
        request.enqueue(object : Callback<GameDetailResponse>{
            override fun onResponse(
                call: Call<GameDetailResponse>,
                response: Response<GameDetailResponse>
            ) {
                if (response.isSuccessful) view?.onGetDetail(response.body())
            }

            override fun onFailure(call: Call<GameDetailResponse>, t: Throwable) {
                Log.e("afas", t.message.toString())
            }
        })
    }
}