package com.example.newapplication.data

import com.example.newapplication.model.Data
import com.example.newapplication.model.Employee
import retrofit2.Response
import retrofit2.http.GET

interface ApiInterface {

    @GET("employees")
    suspend fun getEmployee(): Response<Employee>
}