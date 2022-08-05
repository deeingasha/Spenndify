package com.example.spenndify.login

import android.os.Bundle
import android.os.Handler
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.compose.runtime.snapshots.Snapshot.Companion.observe
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.spenndify.VerifyPopup
import com.example.spenndify.createaccount.CreatePinFragmentDirections
import com.example.spenndify.createaccount.SecurityQuestionFragmentDirections
import com.example.spenndify.data.remote.model.OtpRequest
import com.example.spenndify.data.remote.model.response.OTP
import com.example.spenndify.databinding.AccLookupBinding
import com.example.spenndify.utils.Status
import com.example.spenndify.utils.showToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AccLookup: Fragment() {
    private lateinit var binding: AccLookupBinding
    private val viewModel:AccLookupViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = AccLookupBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.phoneNo.requestFocus()

        binding.lookupContinueBtn.setOnClickListener {


            if (validateForm()) {
                val phoneNum = binding.phoneNo.text.toString()

                val otpRequest = OtpRequest(
                    phoneNumber = "+254$phoneNum"
                )
                requestOTP(otpRequest)
                val popup = VerifyPopup
                val action = AccLookupDirections.actionAccLookupToLoginWelcomeFragment()
                findNavController().navigate(action)
                Handler().postDelayed({
                    popup.dialog.dismiss()
                    popup.timeCountdown.cancel()

                },6000)

                popup.createVerifyPopup(context)
3
                var phoneStart = phoneNum?.subSequence(0, 4)
                var phoneEnd = phoneNum?.subSequence(7, 9)
                popup.numberText.text =
                    "We’ve sent a verification code to +254${phoneStart}***${phoneEnd}" //TODO figure out sth better
                popup.timeCountdown.start()
            }

        }

        binding.lookupCancelBtn.setOnClickListener {
            val action = AccLookupDirections.actionAccLookupToOnboardingFrag()
            findNavController().navigate(action)
        }
    }
    private fun validateForm():Boolean{

        val phoneNo = binding.phoneNo.text.toString()

        binding.apply {
            when {

                phoneNo.isEmpty() -> {
                    textInputLayout.isErrorEnabled = true
                    textInputLayout.error = "Please input phone number"
                    return false
                }
                phoneNo.length<9 -> {
                    textInputLayout.isErrorEnabled = true
                    textInputLayout.error = "Phone number should be 9 numbers"
                    return false
                }
                else -> return true
            }
        }
    }

    fun  requestOTP(otpRequest: OtpRequest){
        viewModel.requestOTP(otpRequest).observe(viewLifecycleOwner){
            binding.apply {
                it.let { resource ->
                    when(resource.status){
                        Status.SUCCESS -> {
                            Toast.makeText(context, "works!!", Toast.LENGTH_SHORT).show()

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
    //TODO set up broadcast receiver and read otp
    fun  verifyOTP(otp: OTP){
        viewModel.sendOTP(otp).observe(viewLifecycleOwner){
            binding.apply {
                it.let { resource ->
                    when(resource.status){
                        Status.SUCCESS -> {
                            Toast.makeText(context, "works!!", Toast.LENGTH_SHORT).show()

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
}