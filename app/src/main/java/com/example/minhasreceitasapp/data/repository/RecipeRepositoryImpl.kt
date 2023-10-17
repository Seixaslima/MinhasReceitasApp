package com.example.minhasreceitasapp.data.repository

import com.example.minhasreceitasapp.data.dao.RecipeDao
import com.example.minhasreceitasapp.data.mapper.toDomain
import com.example.minhasreceitasapp.data.mapper.toEntity
import com.example.minhasreceitasapp.domain.model.RecipeDomain
import com.example.minhasreceitasapp.domain.repository.RecipeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RecipeRepositoryImpl(
    private val dao: RecipeDao
): RecipeRepository {
    override suspend fun getAll(): List<RecipeDomain>  = withContext(Dispatchers.IO) {
        dao.getAll().map {
            it.toDomain()
        }
    }

    override suspend fun insert(recipe: RecipeDomain) = withContext(Dispatchers.IO) {
        dao.insert(recipe.toEntity())
    }
}