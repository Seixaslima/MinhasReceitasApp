package com.example.minhasreceitasapp.presentation.recipe.adapter

import com.example.minhasreceitasapp.domain.model.RecipeDomain

sealed interface RecipeState {
    object Loading: RecipeState
    object Empty: RecipeState
    data class Success (val recipe: List<RecipeDomain>): RecipeState
    data class Error (val message: String): RecipeState
}
