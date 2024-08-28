package com.example.newapplication.data

import com.example.newapplication.model.Data
import com.example.newapplication.model.Employee
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ApiInterface {

    @GET("employees")
    suspend fun getEmployee(): Response<Employee>

    @POST("create")
    suspend fun createEmployee(
        @Body user: Employee
    ): Response<Employee>
    @PUT("update/{id}")
    suspend fun updateEmployee(@Path("id") id: Int, @Body employee: Employee): Response<Employee>

    @DELETE("delete/{id}")
    suspend fun deleteEmployee(@Path("id") id: Int): Response<Employee>

}