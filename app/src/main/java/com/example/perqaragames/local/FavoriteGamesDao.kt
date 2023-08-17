package com.example.perqaragames.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface FavoriteGamesDao {
    @Insert
    fun addToFavorite(data: FavoriteData)

    @Query("SELECT * FROM favorite_games")
    fun getFavoriteMovie(): LiveData<List<FavoriteData>>

    @Query("SELECT count(*) FROM favorite_games WHERE favorite_games.idGames = :id")
    fun checkGame(id: Int): Int

    @Query("DELETE FROM favorite_games WHERE favorite_games.idGames = :id")
    fun removeFromFavorite(id: Int): Int
}