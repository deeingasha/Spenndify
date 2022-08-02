package com.example.spenndify.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.spenndify.databinding.LoginWelcomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginWelcomeFragment:Fragment() {
    private lateinit var binding: LoginWelcomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = LoginWelcomeBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            //TODO change the username
            login2Btn.setOnClickListener{
                val action = LoginWelcomeFragmentDirections.actionLoginWelcomeFragmentToLoginPinFragment()
                findNavController().navigate(action)
            }
        }
           }
}