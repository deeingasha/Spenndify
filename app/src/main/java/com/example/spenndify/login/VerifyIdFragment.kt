package com.example.spenndify.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.spenndify.databinding.VerifyIdBinding

class VerifyIdFragment:Fragment() {
    lateinit var binding: VerifyIdBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = VerifyIdBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //TODO what happens on click?
        binding.continueViBtn.setOnClickListener {
            if (validateForm()){
            Toast.makeText(context, "Coming Soon", Toast.LENGTH_LONG).show()
        }
            binding.backViBtn.setOnClickListener {
                findNavController().navigateUp()
            }
        }
    }

    private fun validateForm():Boolean{

        val id_answer = binding.idInputViTxt.text.toString()

        binding.apply {
            return when {
                id_answer.isEmpty() -> {
                    idInputViLayout.isErrorEnabled = true
                    idInputViLayout.error = "Please answer"
                    false
                }
                else -> true
            }
        }
    }
}