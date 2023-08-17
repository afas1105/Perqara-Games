package com.example.perqaragames.local

import javax.inject.Inject

class FavoriteRepositories @Inject constructor(
    private val favoriteGamesDao: FavoriteGamesDao
) {
    fun addToFavorite(favoriteData: FavoriteData) = favoriteGamesDao.addToFavorite(favoriteData)

    fun getAllFavorite() = favoriteGamesDao.getFavoriteMovie()

    fun checkFavorite(id: Int) = favoriteGamesDao.checkGame(id)

    fun removeFavorite(id: Int) = favoriteGamesDao.removeFromFavorite(id)
}