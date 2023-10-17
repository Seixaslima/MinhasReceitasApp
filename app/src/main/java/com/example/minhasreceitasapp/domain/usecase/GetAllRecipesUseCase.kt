package com.example.minhasreceitasapp.domain.usecase

import com.example.minhasreceitasapp.domain.repository.RecipeRepository

class GetAllRecipesUseCase constructor(
    private val repository: RecipeRepository
) {
    suspend operator fun invoke() = repository.getAll()
}