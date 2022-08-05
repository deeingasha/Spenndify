package com.example.spenndify.addtransaction

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.spenndify.R
import com.example.spenndify.bottomsheets.EnterAmount
import com.example.spenndify.bottomsheets.EnterAmount.Companion.TAG
import com.example.spenndify.bottomsheets.SmsPermission
import com.example.spenndify.databinding.AddExpenseLayoutBinding
import com.example.spenndify.databinding.DashboardFragBinding
import com.example.spenndify.onboarding.OnboardingItemAdapter
import com.example.spenndify.onboarding.OnboardingItemModel
import com.google.android.material.tabs.TabLayoutMediator

class AddTransactionFragment:Fragment() {

    private lateinit var binding: AddExpenseLayoutBinding
    private lateinit var transactionLayoutAdapter: TransactionLayoutAdapter

    private lateinit var viewPager: ViewPager2

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        binding = AddExpenseLayoutBinding.inflate(inflater, container, false)
        setTransactionLayoutItems()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val tabNameArray = arrayOf(
            "INCOME",
            "EXPENSE",
            "BUDGET"
        )

        val tabLayout = binding.transactionTabs
        viewPager = binding.transactionPager
        binding.transactionPager.adapter = transactionLayoutAdapter
        TabLayoutMediator(tabLayout,viewPager){tab,position ->
            tab.text = tabNameArray[position]
         }.attach()


        binding.backAtBtn.setOnClickListener {
            findNavController().navigateUp()
        }

    }

    private fun setTransactionLayoutItems() {

        transactionLayoutAdapter = TransactionLayoutAdapter(childFragmentManager, lifecycle)

    }
}