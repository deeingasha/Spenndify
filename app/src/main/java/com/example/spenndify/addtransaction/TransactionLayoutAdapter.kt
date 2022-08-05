package com.example.spenndify.addtransaction

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.spenndify.databinding.OnboardingLayoutBinding
import com.example.spenndify.databinding.TransactionOptionsLayoutBinding
import com.example.spenndify.onboarding.OnboardingItemAdapter
import com.example.spenndify.onboarding.OnboardingItemModel

private const val NUM_TABS = 3

class TransactionLayoutAdapter (fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle){

    override fun getItemCount(): Int {
        return NUM_TABS
    }

    override fun createFragment(position: Int): Fragment {
        when (position) {
            0 -> return AddIncomeTransaction()
            1 -> return AddExpenseTransaction()
        }
        return AddBudget()
    }
}

