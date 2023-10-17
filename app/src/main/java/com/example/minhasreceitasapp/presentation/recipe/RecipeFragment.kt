package com.example.minhasreceitasapp.presentation.recipe

import android.os.Binder
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.example.minhasreceitasapp.R
import com.example.minhasreceitasapp.databinding.FragmentRecipeBinding
import com.example.minhasreceitasapp.presentation.recipe.adapter.RecipeAdapter
import com.example.minhasreceitasapp.presentation.recipe.adapter.RecipeState
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
        observerState()
    }

    private fun setupListener () {
        binding.fabRecipe.setOnClickListener{
            //TODO show Dialog
        }
    }

    private fun setupAdapter () {
        binding.rvRecipes.adapter = adapter
    }

    private fun observerState() {
        viewModel.state.observe(viewLifecycleOwner) { state ->
            when (state) {
                RecipeState.Loading -> {
                    binding.pbLoading.isVisible = true
                }
                RecipeState.Empty -> {
                    binding.pbLoading.isVisible = false
                    Toast.makeText(
                        requireContext(),
                        getString(R.string.label_empty_recipes),
                        Toast.LENGTH_LONG
                    ).show()
                }
                is RecipeState.Error -> {
                    binding.pbLoading.isVisible = false
                    Toast.makeText(
                        requireContext(),
                        state.message,
                        Toast.LENGTH_LONG
                    ).show()
                }
                is RecipeState.Success -> {
                    binding.pbLoading.isVisible = false
                    adapter.submitList(state.recipe)
                }
            }

        }
    }

}