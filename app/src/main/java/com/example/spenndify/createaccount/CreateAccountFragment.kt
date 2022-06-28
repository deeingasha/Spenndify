package com.example.spenndify.createaccount

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.spenndify.databinding.AccCreationBinding


class CreateAccountFragment: Fragment() {
    private lateinit var binding: AccCreationBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = AccCreationBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        binding.continueCaBtn.setOnClickListener {

            val firstName = (binding.fName.text).toString()
            val lastName = binding.lName.text.toString()
            val idNo = binding.idNo.text.toString()
            val email = binding.email.text.toString()

           if (firstName.length == 0){
               binding.fnameInputLayout.isErrorEnabled = true
                binding.fnameInputLayout.error = "Please input first name"
            }else if (lastName.length == 0){
                binding.lnameInputLayout.isErrorEnabled = true
                binding.lnameInputLayout.error = "Please input last name"
            }else if (idNo.length == 0){
                binding.idnoInputLayout.isErrorEnabled = true
                binding.idnoInputLayout.error = "Please input ID number"
            }else if (idNo.length <8){
                binding.idnoInputLayout.isErrorEnabled = true
                binding.idnoInputLayout.error = "ID number should be 8 characters"
           }else if (email.length == 0){
               binding.emailInputLayout.isErrorEnabled = true
               binding.emailInputLayout.error= "Please input email address"
           }
           else {
               val action =
                   CreateAccountFragmentDirections.actionCreateAccountFragmentToSecurityQuestionFragment(firstName,lastName)
               findNavController().navigate(action)
           }

        }

        binding.backCaBtn.setOnClickListener {
            val action = CreateAccountFragmentDirections.actionCreateAccountFragmentToOnboardingFrag()
                findNavController().navigate(action)
        }


    }
}