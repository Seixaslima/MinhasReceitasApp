package com.example.minhasreceitasapp.presentation.recipe.adapter

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.minhasreceitasapp.data.db
import com.example.minhasreceitasapp.data.repository.RecipeRepositoryImpl
import com.example.minhasreceitasapp.domain.model.RecipeDomain
import com.example.minhasreceitasapp.domain.usecase.GetAllRecipesUseCase
import com.example.minhasreceitasapp.domain.usecase.InsertRecipesUseCase
import kotlinx.coroutines.launch

class RecipesViewModel(
    private val getAllRecipesUseCase: GetAllRecipesUseCase,
    private val insertRecipesUseCase: InsertRecipesUseCase
): ViewModel() {
    val state: LiveData<RecipeState> = liveData {
        emit(RecipeState.Loading)
        val state = try {
            val recipes = getAllRecipesUseCase()
            if(recipes.isEmpty()) {
                RecipeState.Empty
            } else {
                RecipeState.Success(recipes)
            }
        } catch (exception: Exception) {
            Log.e("Error", exception.message.toString())
            RecipeState.Error(exception.message.toString())
        }
        emit(state)
    }

    fun insert(name: String) = viewModelScope.launch {
        insertRecipesUseCase(RecipeDomain(name=name))
    }

    class Factory: ViewModelProvider.Factory {
        override fun <T: ViewModel> create(
            modelClass: Class<T>,
            extras: CreationExtras
        ): T {
            val application = checkNotNull(extras[APPLICATION_KEY])
            val repository = RecipeRepositoryImpl(application.db.recipeDao())
            return RecipesViewModel (
                getAllRecipesUseCase = GetAllRecipesUseCase(repository),
                insertRecipesUseCase = InsertRecipesUseCase(repository)
            ) as T
        }
    }

}