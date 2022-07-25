package com.example.spenndify.dashboard

import android.os.Bundle
import android.text.TextUtils
import android.text.TextUtils.replace
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.spenndify.R
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
        val homeFragment = HomeFragment()
        val expenseFragment = ExpenseFragment()
        val budgetFragment = BudgetFragment()
        val historyFragment = HistoryFragment()

        setCurrentFragment(homeFragment)

        binding.bottomNavView.setOnNavigationItemSelectedListener {

            when(it.itemId){
                R.id.nav_home->setCurrentFragment(homeFragment)
                R.id.nav_expenses->setCurrentFragment(expenseFragment)
                R.id.nav_budget->setCurrentFragment(budgetFragment)
                R.id.nav_history->setCurrentFragment(historyFragment)
            }
            true
        }
        return binding.root
    }
    private fun setCurrentFragment(fragment:Fragment)=
        fragmentManager?.beginTransaction()?.apply {
            replace(R.id.dashFragment, fragment)
            commit()
        }
}