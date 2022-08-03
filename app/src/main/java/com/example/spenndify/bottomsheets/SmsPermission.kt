package com.example.spenndify.bottomsheets

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.spenndify.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

public class SmsPermission: BottomSheetDialogFragment() {

    companion object {
        const val TAG = "SmsBottomSheet"
    }


    @Override
    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.sms_permission_layout, container, false);
    }