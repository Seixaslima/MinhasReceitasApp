package com.example.minhasreceitasapp.domain.repository

import com.example.minhasreceitasapp.domain.model.RecipeDomain


interface RecipeRepository {

    suspend fun getAll(): List<RecipeDomain>
    suspend fun insert(recipe: RecipeDomain)
}