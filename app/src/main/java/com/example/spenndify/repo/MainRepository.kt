package com.example.spenndify.repo

import com.example.spenndify.data.remote.ApiService
import com.example.spenndify.data.remote.model.OtpRequest
import com.example.spenndify.data.remote.model.response.OTP
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val api:ApiService
){

    suspend fun  getOTP(otpRequest: OtpRequest) = api.getOTP(otpRequest )

    suspend fun confirmOTP(otp: OTP) = api.confirmOTP(otp)

    suspend fun updateNewPin(pin:String) = api.updateNewPin(pin)
}