package com.example.spenndify.createaccount

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.spenndify.R
import com.example.spenndify.VerifyPopup
import com.example.spenndify.databinding.SecurityQuestionsBinding

class SecurityQuestionFragment:Fragment() {
    private lateinit var binding: SecurityQuestionsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = SecurityQuestionsBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpQuestions()

        binding.continueSqBtn.setOnClickListener {

            val popup = VerifyPopup
            popup.createVerifyPopup(context)
            popup.timeCountdown.start()
        }
        binding.backSqBtn.setOnClickListener {
            val action = SecurityQuestionFragmentDirections.actionSecurityQuestionFragmentToCreateAccountFragment()
                findNavController().navigate(action)
        }
    }

    private fun setUpQuestions(){
        val items = listOf("What is your favorite food?","What is your favorite color?", "Name of pet Dog?", "Name of favorite teacher?")
        val adapter = ArrayAdapter(requireContext(), R.layout.list_security_questions_layout,items)
        (binding.lQ2.editText as? AutoCompleteTextView)?.setAdapter(adapter)
        (binding.lQ3.editText as? AutoCompleteTextView)?.setAdapter(adapter)
    }

}
//TODO change so that input field shows up when text is selected