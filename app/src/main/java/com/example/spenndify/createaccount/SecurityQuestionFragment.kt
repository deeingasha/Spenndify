package com.example.spenndify.createaccount

import android.os.Bundle
import android.os.Handler
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.view.isEmpty
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.spenndify.R
import com.example.spenndify.VerifyPopup
import com.example.spenndify.databinding.SecurityQuestionsBinding

class SecurityQuestionFragment:Fragment() {
    private lateinit var binding: SecurityQuestionsBinding
    private val args: SecurityQuestionFragmentArgs by navArgs()
    private val popup = VerifyPopup

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
        setUpAnswerEditText()

        //TODO get Phone Number

        binding.continueSqBtn.setOnClickListener {

           if (validateForm()) {

                hideErrorMessage()
                Handler().postDelayed({
                        popup.dialog.dismiss()
                        popup.timeCountdown.cancel()

                    //TODO change back to actually verifying number before moving on
                         val action = SecurityQuestionFragmentDirections.actionSecurityQuestionFragmentToCreatePinFragment(args.fname,args.lname,args.phoneNo)
                         findNavController().navigate(action)
                    },6000)

               popup.createVerifyPopup(context)
               val phoneNumber = args.phoneNo
               var phoneStart =phoneNumber?.subSequence(0,4)
               var phoneEnd = phoneNumber?.subSequence(7,9)
               popup.numberText.text = "We’ve sent a verification code to +254${phoneStart}***${phoneEnd}"
               popup.timeCountdown.start()
                //TODO fix the phone number part
           //TODO uncomment validation
            }
            /**val action = SecurityQuestionFragmentDirections.actionSecurityQuestionFragmentToCreatePinFragment(args.fname,args.lname,args.phoneNo)
            findNavController().navigate(action)*/
        }
        binding.backSqBtn.setOnClickListener {
                findNavController().navigateUp()
        }
    }

    private fun setUpQuestions(){
        binding.apply {
            lQ1.requestFocus()
            val items = listOf(
                "What is your passion?",
                "What is your favorite food?",
                "What is your favorite color?",
                "Name of pet Pet?",
                "Name of favorite teacher?"
            )
            val adapter = ArrayAdapter(requireContext(), R.layout.list_security_questions_layout,items)
            (lQ1.editText as? AutoCompleteTextView)?.setAdapter(adapter)
            (lQ2.editText as? AutoCompleteTextView)?.setAdapter(adapter)
            (lQ3.editText as? AutoCompleteTextView)?.setAdapter(adapter)
        }
    }

    private fun setUpAnswerEditText(){
        binding.q2.setOnClickListener{showAnswerEditText()}
        binding.q3.setOnClickListener{showAnswerEditText()}
    }
    private fun showAnswerEditText(){
        if (!binding.textInputLayouta2.isVisible){
            binding.textInputLayouta2.visibility = View.VISIBLE
        }else if (!binding.textInputLayouta3.isVisible){
            binding.textInputLayouta3.visibility = View.VISIBLE
        }
    }

    private fun validateForm():Boolean{
        binding.apply {
            when {
                a1.text.isNullOrEmpty() -> {
                    a1.visibility = View.VISIBLE
                    textInputLayouta1.isErrorEnabled = true
                    textInputLayouta1.error =getString(R.string.please_input_answer)
                    return false
                }
                textInputLayouta2.visibility==View.VISIBLE && binding.a2.text.isNullOrEmpty() -> {
                    textInputLayouta2.isErrorEnabled = true
                    textInputLayouta2.error = getString(R.string.please_input_answer)
                    return false
                }
                textInputLayouta3.visibility==View.VISIBLE && binding.a3.text.isNullOrEmpty() -> {
                    textInputLayouta3.isErrorEnabled = true
                    textInputLayouta3.error = getString(R.string.please_input_answer)
                    return false
                }
                else -> return true
            }
        }
    }

    private fun hideErrorMessage(){
        binding.textInputLayouta1.error = null
        binding.textInputLayouta2.error = null
        binding.textInputLayouta3.error = null
    }

}
//TODO still carry forward name and ID

