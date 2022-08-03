package com.example.spenndify.addtransaction

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.spenndify.databinding.OnboardingLayoutBinding
import com.example.spenndify.databinding.TransactionOptionsLayoutBinding
import com.example.spenndify.onboarding.OnboardingItemAdapter
import com.example.spenndify.onboarding.OnboardingItemModel

class TransactionLayoutAdapter (
    private val TransactionItems: List<TransactionItemsModel>
): RecyclerView.Adapter<TransactionLayoutAdapter.TransactionViewHolder>(){

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TransactionViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = TransactionOptionsLayoutBinding.inflate(inflater, parent,false)

        return TransactionViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: TransactionViewHolder,
        position: Int
    ) {
        holder.bind(TransactionItems[position])
    }

    override fun getItemCount(): Int {
        return TransactionItems.size
    }

    inner class TransactionViewHolder(private val binding: TransactionOptionsLayoutBinding
    ): RecyclerView.ViewHolder(binding.root){
        fun  bind(TransactionItems: TransactionItemsModel){

            binding.transactionOptText.text = TransactionItems.transactionText
            binding.setCycleOption.visibility = TransactionItems.setCycleView

        }
    }
}