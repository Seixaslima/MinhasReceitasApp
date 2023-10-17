package com.example.minhasreceitasapp.presentation.recipe

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.minhasreceitasapp.databinding.FragmentRecipeBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class RecipeFragment : Fragment() {

    private lateinit var binding: FragmentRecipeBinding
    private val adapter by lazy {}
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
    }

    private fun setupListener () {
        binding.fabRecipe.setOnClickListener{
            //TODO show Dialog
        }
    }

    private fun setupAdapter () {
        //TODO criar vinculação do adapter
    }

}