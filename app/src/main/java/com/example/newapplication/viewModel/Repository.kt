package com.example.newapplication.viewModel

import com.example.newapplication.model.Employee
import com.example.newapplication.utils.RetrofitInstance
import retrofit2.Response


class Repository(private val db:RetrofitInstance) {

    suspend fun getEmployee(): Response<Employee> {
        val response = db.api.getEmployee()
        return response
    }

    suspend fun createEmployee(user: Employee): Employee{
        val response = db.api.createEmployee(user)
        return response

    }

    suspend fun updateEmployee(id: Int,employee: Employee): Employee{
        val response = db.api.updateEmployee(id,employee)
        return response
    }


    suspend fun deleteEmployee(id: Int):Employee{
        val response = db.api.deleteEmployee(id)
        return response
    }

}