package com.example.spenndify.bottomsheets

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AutoCompleteTextView
import androidx.navigation.fragment.findNavController
import com.example.spenndify.R
import com.example.spenndify.databinding.EnterAmountBsBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.textfield.TextInputLayout

class EnterAmount:BottomSheetDialogFragment() {

    private lateinit var binding: EnterAmountBsBinding


    companion object {
        const val TAG = "AmountBottomSheet"
    }


    @Override
    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?
    ): View? {
        binding = EnterAmountBsBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            cancelEaBtn.setOnClickListener {
                dismiss()
            }
        }
        binding.apply {
            continueEaBtn.setOnClickListener{
                val amount = amountInput.text.toString()
            }
        }
    }
}