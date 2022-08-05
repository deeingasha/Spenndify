package com.example.spenndify.data.remote

import com.example.spenndify.data.remote.model.OtpRequest
import com.example.spenndify.data.remote.model.Security
import com.example.spenndify.data.remote.model.SecurityAnswer
import com.example.spenndify.data.remote.model.response.LoginResponse
import com.example.spenndify.data.remote.model.response.OTP
import com.example.spenndify.data.remote.model.response.UserResponse
import com.example.spenndify.model.User
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST

interface ApiService {

    @POST("spendy/user/registration")
    suspend fun postUserDetails(
        @Body user: User
    ): UserResponse

    @POST("spendy/user/send/otp")
    suspend fun getOTP(
        @Body otpRequest: OtpRequest
    ):UserResponse

    @POST("verify/registration/otp")
    suspend fun confirmOTP(
        @Body otp: OTP
    ): UserResponse

    @PATCH("spendy/user/password/reset")
    suspend fun updateNewPin(
        @Body pin: String
    ): UserResponse

    @POST("spendy/user/authenticate")
    suspend fun loginUser(
        @Body pin: String
    ): LoginResponse
}