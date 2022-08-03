package com.example.spenndify.dashboard

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.spenndify.R
import com.example.spenndify.bottomsheets.SmsPermission
import com.example.spenndify.bottomsheets.SmsPermission.Companion.TAG
import com.example.spenndify.databinding.AccCreationBinding
import com.example.spenndify.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint


class HomeFragment : Fragment() {

    companion object {
        fun newInstance() = HomeFragment()
    }

//    private lateinit var viewModel: HomeViewModel
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater,container,false).apply {
//            viewModel
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            importBtn.setOnClickListener {
                val smsBottomSheet = SmsPermission()
                smsBottomSheet.show(childFragmentManager, TAG)
            }
        }

//        super.onActivityCreated(savedInstanceState)
//        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
//        // TODO: Use the ViewModel
    }
}





