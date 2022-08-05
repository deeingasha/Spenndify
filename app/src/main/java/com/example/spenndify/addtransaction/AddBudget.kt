package com.example.spenndify.addtransaction

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.spenndify.bottomsheets.Categories
import com.example.spenndify.bottomsheets.EnterAmount
import com.example.spenndify.bottomsheets.TimeAndDate
import com.example.spenndify.databinding.TransactionOptionsLayoutBinding

class AddBudget:Fragment() {

    private lateinit var binding:TransactionOptionsLayoutBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = TransactionOptionsLayoutBinding.inflate(inflater,container,false)
        binding.setCycleOption.visibility = View.VISIBLE
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            amountOption.setOnClickListener { addAmount() }
            categoryOption.setOnClickListener { selectCategory() }
            dateOption.setOnClickListener { setTimeDate() }
        }

    }

    fun addAmount(){
        val amountBottomSheet = EnterAmount()
        amountBottomSheet.show(childFragmentManager, EnterAmount.TAG)
    }

    fun selectCategory(){
        val categoriesBottomSheet = Categories()
        categoriesBottomSheet.show(childFragmentManager, Categories.TAG)
    }

    fun setTimeDate(){
        val dateBottomSheet = TimeAndDate()
        dateBottomSheet.show(childFragmentManager, Categories.TAG)
    }
}