package com.example.minhasreceitasapp.domain.usecase

import com.example.minhasreceitasapp.domain.model.RecipeDomain
import com.example.minhasreceitasapp.domain.repository.RecipeRepository

class InsertRecipesUseCase constructor(
    private val repository: RecipeRepository
) {
    suspend operator fun invoke(recipe: RecipeDomain) = repository.insert(recipe)
}