package com.example.spenndify.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.spenndify.databinding.ForgotPinLayoutBinding

class ForgotPinFragment: Fragment() {
    private lateinit var binding: ForgotPinLayoutBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ForgotPinLayoutBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.sqAnswerTxt.requestFocus()

        binding.continueFpBtn.setOnClickListener {
            if (validateForm()){
                //TODO check with backend
                val action = ForgotPinFragmentDirections.actionForgotPinFragmentToCreatePinFragment()
                findNavController().navigate(action)
            }
        }

        binding.otherMethodFpBtn.setOnClickListener {
           val action = ForgotPinFragmentDirections.actionForgotPinFragmentToVerifyIdFragment()
            findNavController().navigate(action)
        }

    }

    private fun validateForm():Boolean{

        val sq_answer = binding.sqAnswerTxt.text.toString()

        binding.apply {
            return when {
                sq_answer.isEmpty() -> {
                    sqAnswerLayout.isErrorEnabled = true
                    sqAnswerLayout.error = "Please answer"
                    false
                }
                else -> true
            }
        }
    }
}