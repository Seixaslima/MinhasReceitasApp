package com.example.minhasreceitasapp.presentation.recipe

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.minhasreceitasapp.databinding.FragmentRecipeBinding
import com.example.minhasreceitasapp.presentation.recipe.adapter.RecipeAdapter
import com.example.minhasreceitasapp.presentation.recipe.adapter.RecipesViewModel

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class RecipeFragment : Fragment() {

    private val viewModel: RecipesViewModel by viewModels {
        RecipesViewModel.Factory()
    }
    private lateinit var binding: FragmentRecipeBinding
    private val adapter by lazy { RecipeAdapter()}
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRecipeBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListener()
        setupAdapter()
    }

    private fun setupListener () {
        binding.fabRecipe.setOnClickListener{
            //TODO show Dialog
        }
    }

    private fun setupAdapter () {
        binding.rvRecipes.adapter = adapter
    }



}