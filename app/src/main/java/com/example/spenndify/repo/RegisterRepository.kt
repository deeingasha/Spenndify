package com.example.spenndify.repo

import com.example.spenndify.data.remote.ApiService
import com.example.spenndify.data.remote.model.Security
import com.example.spenndify.data.remote.model.SecurityAnswer
import com.example.spenndify.model.User
import javax.inject.Inject

class RegisterRepository @Inject constructor(
    private val api:ApiService
){
    suspend fun postUserDetails(user: User) = api.postUserDetails(user)


    suspend fun loginUser(pin: String) = api.loginUser(pin)
}