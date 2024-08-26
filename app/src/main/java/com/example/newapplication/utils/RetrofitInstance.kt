package com.example.newapplication.utils

import com.example.newapplication.data.ApiInterface
import com.example.newapplication.utils.Util.Base
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    val api : ApiInterface by lazy {
        Retrofit.Builder()
            .baseUrl(Base)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiInterface::class.java)

    }
}