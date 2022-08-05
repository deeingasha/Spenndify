package com.example.spenndify.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.spenndify.data.remote.model.OtpRequest
import com.example.spenndify.data.remote.model.response.OTP
import com.example.spenndify.model.User
import com.example.spenndify.repo.MainRepository
import com.example.spenndify.repo.userRepository.UserRepository
import com.example.spenndify.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class AccLookupViewModel @Inject constructor(
    private val mainRepository: MainRepository
):ViewModel() {

    fun  requestOTP(otpRequest: OtpRequest) = liveData(Dispatchers.IO) {

        emit(Resource.loading(data = null))

        try {
            emit(Resource.success(mainRepository.getOTP(otpRequest)))
        }catch (e:Exception){
            emit(Resource.error(e.message?:"Error Occurred",data = null))
        }
    }

    fun  sendOTP(otp: OTP) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))

        try {
            emit(Resource.success(mainRepository.confirmOTP(otp)))
        }catch (e:Exception){
            emit(Resource.error(e.message?:"Error Occurred",data = null))
        }
    }
}