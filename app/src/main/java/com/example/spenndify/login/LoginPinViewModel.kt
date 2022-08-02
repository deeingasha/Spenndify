package com.example.spenndify.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.spenndify.repo.RegisterRepository
import com.example.spenndify.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class LoginPinViewModel @Inject constructor(
    private val registerRepository: RegisterRepository
):ViewModel() {
    fun loginUser(pin:String) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))

        try {
            emit(Resource.success(data = registerRepository.loginUser(pin)))
        } catch (e: Exception) {

            emit(Resource.error(message = e.message ?: "Error occurred", data = null))
        }
    }

}