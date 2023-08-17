package com.example.perqaragames.network

import com.example.perqaragames.model.GameDetailResponse
import com.example.perqaragames.model.GamesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

//const val TOKEN = "03a9613bf66e42a0826525deb4e32fc1"

interface ClientInterface {
    @GET("api/games")
    fun getListGames(
        @Query("page") page: Int? = null,
        @Query("page_size") pageSize: Int? = null,
        @Query("search") search: String? = "",
        @Query("key") key: String = "03a9613bf66e42a0826525deb4e32fc1"
    ): Call<GamesResponse>

    @GET("api/games/{id}")
    fun getDetailGames(
        @Path("id") id: Int,
        @Query("key") key: String = "03a9613bf66e42a0826525deb4e32fc1"
    ): Call<GameDetailResponse>
}