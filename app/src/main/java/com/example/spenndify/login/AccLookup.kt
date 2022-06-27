package com.example.spenndify.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.spenndify.VerifyPopup
import com.example.spenndify.databinding.AccLookupBinding

class AccLookup: Fragment() {
    private lateinit var binding: AccLookupBinding

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
            val popup = VerifyPopup
            popup.createVerifyPopup(context)
            popup.timeCountdown.start()
        }

        binding.lookupCancelBtn.setOnClickListener {
            val action = AccLookupDirections.actionAccLookupToOnboardingFrag()
            findNavController().navigate(action)
        }
    }

}