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
//TODO
//    suspend fun getSecurityQuestions(errorText:(String) -> Unit) : List<Security>? {
//
//        return try {
//            val securities = api.getSecurityQuestions()
//
//            securities
//        } catch (e: Exception) {
//
//            errorText(e.toString())
//            null
//        }
//    }
//
//    suspend fun postSecurityAnswers(securityAnswer: SecurityAnswer) = api.postSecurityAnswers(securityAnswer)

    suspend fun loginUser(pin: String) = api.loginUser(pin)
}