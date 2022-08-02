package com.example.spenndify.createaccount

import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.spenndify.databinding.AccCreationBinding
import com.example.spenndify.model.User
import com.example.spenndify.utils.Status
import com.example.spenndify.utils.showToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CreateAccountFragment: Fragment() {

    private lateinit var binding: AccCreationBinding
    private val viewModel:CreateAccountViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = AccCreationBinding.inflate(inflater,container,false).apply {
            viewModel
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            continueCaBtn.setOnClickListener {

                val firstName= fName.text.toString()
                val lastName = lName.text.toString()
                val idNum = idNo.text.toString()
                val emailAdd = emailInput.text.toString()
                val phoneNumber = phoneNo.text.toString()
//
//                val user = User(
//                    firstName = firstName,
//                    lastName = lastName,
//                    idNumber = idNum,
//                    email = emailAdd,
//                    phone = "+254${phoneNumber}", //TODO work on it
//                    questionOne = "Test",
//                    questionTwo = "Test",
//                    questionThree = "Test",
//                    password = "0000"
//                )

//                if (validateForm()) {
//
//                    hideErrorMessage()
//                    saveUserDetails(user)
//
//                }
                //TODO() uncomment validation

                val action =
                CreateAccountFragmentDirections.actionCreateAccountFragmentToSecurityQuestionFragment(
                binding.fName.text.toString(),
                binding.lName.text.toString(),
                binding.phoneNo.text.toString())
                findNavController().navigate(action)
            }

            backCaBtn.setOnClickListener {
                findNavController().navigateUp()

            }
        }

    }
    //TODO work on this function
    private fun saveUserDetails(user: User){
        viewModel.saveUserDetails(user).observe(viewLifecycleOwner){
            binding.apply {
                it.let { resource ->
                    when(resource.status){
                        Status.SUCCESS -> {
                            Toast.makeText(context, "works!!", Toast.LENGTH_SHORT).show()

                            val action =
                                CreateAccountFragmentDirections.actionCreateAccountFragmentToSecurityQuestionFragment(
                                    binding.fName.text.toString(),
                                    binding.lName.text.toString(),
                                    binding.phoneNo.text.toString()
                                )
                            findNavController().navigate(action)
                        }

                        Status.ERROR -> {
                            showToast(resource.message.toString())
                        }
                        Status.LOADING ->{
                            Toast.makeText(context, "Loading", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        }
    }
    private fun validateForm():Boolean{
        val firstName = (binding.fName.text).toString()
        val lastName = binding.lName.text.toString()
        val idNo = binding.idNo.text.toString()
        val email = binding.emailInput.text.toString()
        val phoneNo = binding.phoneNo.text.toString()

        binding.apply {
            when {
                firstName.isEmpty() -> {
                    fnameInputLayout.isErrorEnabled = true
                    fnameInputLayout.error = "Please input first name"
                    return false
                }
                lastName.isEmpty() -> {
                    lnameInputLayout.isErrorEnabled = true
                    lnameInputLayout.error = "Please input last name"
                    return false
                }
                idNo.isEmpty() -> {
                    idnoInputLayout.isErrorEnabled = true
                    idnoInputLayout.error = "Please input ID number"
                    return false
                }
                idNo.length <7 -> {
                    idnoInputLayout.isErrorEnabled = true
                    idnoInputLayout.error = "ID number should be at least 7 characters"
                    return false
                }
                email.isEmpty() -> {
                    emailInputLayout.isErrorEnabled = true
                    emailInputLayout.error= "Please input email address"
                    return false
                }
                !Patterns.EMAIL_ADDRESS.matcher(email).matches() -> {
                    emailInputLayout.isErrorEnabled = true
                    emailInputLayout.error= "Please input a valid email address"
                    return false
                }
                phoneNo.isEmpty() -> {
                    phoneNoLayout.isErrorEnabled = true
                    phoneNoLayout.error = "Please input phone number"
                    return false
                }
                phoneNo.length<9 -> {
                    phoneNoLayout.isErrorEnabled = true
                    phoneNoLayout.error = "Phone number should be 9 numbers"
                    return false
                }
                //TODO figure this out still proceeds despite error
                else -> return true
            }
        }
    }

    private fun hideErrorMessage(){
        binding.apply {
            fnameInputLayout.isErrorEnabled = false
            lnameInputLayout.isErrorEnabled = false
            idnoInputLayout.isErrorEnabled = false
            emailInputLayout.isErrorEnabled = false
            phoneNoLayout.isErrorEnabled = false
        }
    }
}