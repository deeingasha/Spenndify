package com.example.spenndify.bottomsheets

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.spenndify.R
import com.example.spenndify.databinding.SmsPermissionLayoutBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

public class SmsPermission: BottomSheetDialogFragment() {

    private lateinit var binding:SmsPermissionLayoutBinding

    companion object {
        const val TAG = "SmsBottomSheet"
    }


    @Override
    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?
    ): View? {
        binding = SmsPermissionLayoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.permissionCancelBtn.setOnClickListener {
            dismiss()
        }
    }

}