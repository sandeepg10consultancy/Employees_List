package com.example.newapplication.viewModel

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newapplication.model.Data
import com.example.newapplication.model.Employee
import com.example.newapplication.utils.RetrofitInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

class EmployeeViewModel(private val repository: Repository):ViewModel() {

    //var response : Response<Employee> = RespEmployee(listOf(Data(0,"",0,0,"")),"","")>
    suspend fun getEmployee(): List<Data> {
        return repository.getEmployee().body()!!.data
    }

    fun createEmployee(user: Employee,context: Context){
        viewModelScope.launch {
             try {
                //apiCall()
                repository.createEmployee(user)
                 getEmployee()
            } catch (e: IOException) {
                Toast.makeText(context,"Adding Employee Failed, error:${e.message}",Toast.LENGTH_LONG).show()
            } catch (e: HttpException) {
                 Toast.makeText(context,"Adding Employee Failed, error:${e.message}",Toast.LENGTH_LONG).show()
            }

        }
    }

    fun updateEmployee(id: Int, employee: Employee,context: Context) {
        viewModelScope.launch {
            try {
                //apiCall()
                repository.updateEmployee(id, employee)
                getEmployee()
            } catch (e: IOException) {
                Toast.makeText(context,"Unable to update, error:${e.message}",Toast.LENGTH_LONG).show()
            } catch (e: HttpException) {
                Toast.makeText(context,"Unable to update, error:${e.message}",Toast.LENGTH_LONG).show()
            }
        }
    }

    fun deleteEmployee(id: Int,context: Context) {
        viewModelScope.launch {
            try {
                //apiCall()
                repository.deleteEmployee(id)
                getEmployee()
            } catch (e: IOException) {
                Toast.makeText(context,"Unable to Delete, error:${e.message}",Toast.LENGTH_LONG).show()
            } catch (e: HttpException) {
                Toast.makeText(context,"Unable to Delete, error:${e.message}",Toast.LENGTH_LONG).show()
            }
        }
    }

}