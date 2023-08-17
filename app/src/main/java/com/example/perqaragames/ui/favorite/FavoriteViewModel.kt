package com.example.perqaragames.ui.favorite

import androidx.lifecycle.ViewModel
import com.example.perqaragames.local.FavoriteData
import com.example.perqaragames.local.FavoriteRepositories
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val repositories: FavoriteRepositories
): ViewModel() {
    val getFavorite = repositories.getAllFavorite()

    fun addToFavorites(favoriteData: FavoriteData) {
        CoroutineScope(Dispatchers.IO).launch {
            repositories.addToFavorite(
                FavoriteData(
                    favoriteData.idGames,
                    favoriteData.name,
                    favoriteData.backgroundImage,
                    favoriteData.rating,
                    favoriteData.released
                )
            )
        }
    }

    fun checkFavorite(id: Int) = repositories.checkFavorite(id)

    fun removeFromFavorites(id: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            repositories.removeFavorite(id)
        }
    }
}