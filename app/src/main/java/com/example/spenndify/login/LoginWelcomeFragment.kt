package com.example.spenndify.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.spenndify.databinding.LoginWelcomeBinding

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

}