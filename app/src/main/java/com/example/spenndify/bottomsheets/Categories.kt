package com.example.spenndify.bottomsheets

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.spenndify.R
import com.example.spenndify.databinding.SelectCategoryBsBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class Categories:BottomSheetDialogFragment() {

    private lateinit var binding: SelectCategoryBsBinding

    companion object {
        const val TAG = "CategoryBottomSheet"
    }


    @Override
    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?
    ): View? {
        binding = SelectCategoryBsBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.cancelScBtn.setOnClickListener {
            dismiss()
        }
    }
}