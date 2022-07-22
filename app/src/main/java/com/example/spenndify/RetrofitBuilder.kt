package com.example.spenndify

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilder {
    val baseUrl = "https://login-herokuu.herokuapp.com/"

    fun getInstance(): Retrofit {
        return Retrofit.Builder().baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}