package com.example.spenndify.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.spenndify.databinding.DashboardFragBinding

class DashboardFragment:Fragment() {

    private lateinit var binding:DashboardFragBinding
    private lateinit var dashboardItemAdapter: DashboardItemAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DashboardFragBinding.inflate(inflater,container,false)

        return binding.root
    }


}