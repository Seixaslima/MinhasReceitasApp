package com.example.minhasreceitasapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.minhasreceitasapp.data.dao.RecipeDao
import com.example.minhasreceitasapp.data.entity.RecipeEntity

@Database(entities =
    [
        RecipeEntity::class
    ],
    version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun recipeDao(): RecipeDao
}