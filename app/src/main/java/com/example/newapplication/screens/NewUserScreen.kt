package com.example.newapplication.screens

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.newapplication.model.Data
import com.example.newapplication.model.Employee
import com.example.newapplication.utils.RetrofitInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

@Composable
fun NewUserScreen(navController: NavHostController) {

    val ageTextField = remember {
        mutableStateOf(0)
    }
    val nameTextField = remember {
        mutableStateOf("")
    }
    val salaryTextField = remember {
        mutableStateOf(0)
    }
    val idTextField = remember {
        mutableStateOf(0)
    }
    val imageTextField = remember {
        mutableStateOf("")
    }
    var ageField by remember {
        mutableStateOf(0)
    }
    var nameField by remember {
        mutableStateOf("")
    }
    var salaryField by remember {
        mutableStateOf(0)
    }
    var idField by remember {
        mutableStateOf(0)
    }
    var imageField by remember {
        mutableStateOf("")
    }
    var dialogStatus by remember {
        mutableStateOf(false)
    }
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .padding(20.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center
    ){
        TextField(value = ageTextField.value.toString(),
            onValueChange = {
                ageTextField.value = it.toInt()
            },
            label = { Text(text = "Enter Your Age")},
            modifier = Modifier.width(300.dp),
            colors = TextFieldDefaults.colors(
                focusedTextColor = Color.Black,
                contentColorFor(backgroundColor = Color.Blue),
                focusedLabelColor = Color.Green,
                unfocusedLabelColor = Color.White),
            textStyle = TextStyle.Default.copy(fontSize = 20.sp),
            maxLines = 2
        )
        Spacer(modifier = Modifier.padding(top = 40.dp))
        TextField(value = nameTextField.value,
            onValueChange = {
                nameTextField.value = it
            },
            label = { Text(text = "Enter Your Name")},
            modifier = Modifier.width(300.dp),
            colors = TextFieldDefaults.colors(
                focusedTextColor = Color.Black,
                contentColorFor(backgroundColor = Color.Blue),
                focusedLabelColor = Color.Green,
                unfocusedLabelColor = Color.White),
            textStyle = TextStyle.Default.copy(fontSize = 20.sp),
            maxLines = 2
        )
        Spacer(modifier = Modifier.padding(top = 40.dp))
        TextField(value = salaryTextField.value.toString(),
            onValueChange = {
                salaryTextField.value = it.toInt()
            },
            label = { Text(text = "Enter Your Salary")},
            modifier = Modifier.width(300.dp),
            colors = TextFieldDefaults.colors(
                focusedTextColor = Color.Black,
                contentColorFor(backgroundColor = Color.Blue),
                focusedLabelColor = Color.Green,
                unfocusedLabelColor = Color.White),
            textStyle = TextStyle.Default.copy(fontSize = 20.sp),
            maxLines = 2
        )
        Spacer(modifier = Modifier.padding(top = 40.dp))
        TextField(value = idTextField.value.toString(),
            onValueChange = {
                idTextField.value = it.toInt()
            },
            label = { Text(text = "Enter Your ID")},
            modifier = Modifier.width(300.dp),
            colors = TextFieldDefaults.colors(
                focusedTextColor = Color.Black,
                contentColorFor(backgroundColor = Color.Blue),
                focusedLabelColor = Color.Green,
                unfocusedLabelColor = Color.White),
            textStyle = TextStyle.Default.copy(fontSize = 20.sp),
            maxLines = 2
        )
        Spacer(modifier = Modifier.padding(top = 40.dp))
        TextField(value = imageTextField.value,
            onValueChange = {
                imageTextField.value = it
            },
            label = { Text(text = "Enter Profile Image URL")},
            modifier = Modifier.width(300.dp),
            colors = TextFieldDefaults.colors(
                focusedTextColor = Color.Black,
                contentColorFor(backgroundColor = Color.Blue),
                focusedLabelColor = Color.Green,
                unfocusedLabelColor = Color.White),
            textStyle = TextStyle.Default.copy(fontSize = 20.sp),
            maxLines = 2
        )
        Spacer(modifier = Modifier.padding(top = 40.dp))
        Button(onClick = {
            ageField = ageTextField.value
            ageTextField.value = 0
            nameField = nameTextField.value
            nameTextField.value = ""
            salaryField = salaryTextField.value
            salaryTextField.value = 0
            idField = idTextField.value
            idTextField.value = 0
            imageField = imageTextField.value
            imageTextField.value = ""
            GlobalScope.launch(Dispatchers.IO){
                val response = try {
                    val user = Employee(listOf( Data(ageField,nameField,salaryField,idField,imageField)),"new one","okk")
                    RetrofitInstance.api.createEmployee(user)
                }catch (e:IOException){
                    dialogStatus = true
                    Toast.makeText(context,"app error: ${e.message}",Toast.LENGTH_SHORT).show()

                    return@launch
                }catch (e:HttpException){
                    Toast.makeText(context,"Http error: ${e.message}",Toast.LENGTH_SHORT).show()

                    return@launch
                }
                if(response.isSuccessful && response.body() != null){
                    withContext(Dispatchers.Main){
                        val employeesList = response.body()!!.data
                        navController.navigate("MainScreen?employeesList=$employeesList")
                    }

                }

            }




        },
            modifier = Modifier.padding(start = 100.dp)) {
            Text(text = "Add")
        }
    }
}
