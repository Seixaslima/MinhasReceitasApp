package com.example.minhasreceitasapp.presentation.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.setFragmentResult
import com.example.minhasreceitasapp.databinding.FragmentDialogEditTextBinding
import java.lang.IllegalStateException

class DialogEditTextFragment: DialogFragment() {

    lateinit var binding: FragmentDialogEditTextBinding
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val titleText = arguments?.getString(TITLE_TEXT) ?: throw IllegalArgumentException("Ops, passe o titulo")
        val placeholderText = arguments?.getString(PLACE_HOLDER) ?: throw IllegalArgumentException("Ops, passe o placeholder")

        return activity?.let {
            binding = FragmentDialogEditTextBinding.inflate(
                requireActivity().layoutInflater
            ).apply {
                etEditText.setHint(placeholderText)
                tvTitle.setText(titleText)
            }

            AlertDialog.Builder(it)
                .setView(binding.root)
                .setPositiveButton("confirmar") {_,_ ->
                    setFragmentResult( FRAGMENT_RESULT, bundleOf(
                        EDIT_TEXT_VALUE to binding.etEditText.text.toString()
                    ))
                }.setNegativeButton( "cancelar" ){_,_ ->
                    dismiss()
                }.create()
        } ?: throw IllegalStateException("A activity não pode ser nula")
    }


    companion object {
        const val TITLE_TEXT = "Title text"
        const val PLACE_HOLDER = "Place holder"

        const val FRAGMENT_RESULT = "FRAGMENT_RESULT"
        const val EDIT_TEXT_VALUE = "EDIT_TEXT"

        fun show (
            title: String,
            placeholder: String,
            fragmentManager: FragmentManager,
            tag: String = DialogEditTextFragment::class.simpleName.toString()
        ) {
            DialogEditTextFragment().apply {
                arguments = bundleOf(
                    TITLE_TEXT to title,
                    PLACE_HOLDER to placeholder
                )
            }.show(
                fragmentManager,
                tag
            )
        }
    }
}