package com.example.perqaragames.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(
    tableName = "favorite_games"
)
data class FavoriteData(
    var idGames: Int?,
    val name: String?,
    val backgroundImage: String?,
    val rating: Double?,
    val released: String?,
): Serializable {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}
