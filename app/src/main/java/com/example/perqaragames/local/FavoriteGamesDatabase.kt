package com.example.perqaragames.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [FavoriteData::class],
    version = 1
)
abstract class FavoriteGamesDatabase: RoomDatabase() {
    abstract fun getFavoriteGamesDao(): FavoriteGamesDao
}