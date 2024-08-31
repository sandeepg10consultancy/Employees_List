package com.example.newapplication.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.newapplication.model.Data
import com.example.newapplication.model.Employee
import com.example.newapplication.utils.RetrofitInstance
import com.example.newapplication.viewModel.EmployeeViewModel
import com.example.newapplication.viewModel.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
//import com.example.newapplication.FetchEmployees as FetchEmployees

@Composable
fun DetailsScreen(
    modifier: Modifier = Modifier,
    name: String?,
    age: Int?,
    salary: Int?,
    id: Int,
    image: String?
) {
    val myContext = LocalContext.current
    Column(
        modifier
            .background(Color.Cyan)
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 45.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        if (name != null){
            val ageTextField = remember {
                mutableStateOf(age)
            }
            val nameTextField = remember {
                mutableStateOf(name)
            }
            val salaryTextField = remember {
                mutableStateOf(salary)
            }
            val idTextField = remember {
                mutableStateOf(id)
            }
            val imageTextField = remember {
                mutableStateOf(image)
            }
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
                TextField(value = imageTextField.value!!,
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
                Row(horizontalArrangement = Arrangement.spacedBy(120.dp)){
                    Button(onClick = {
                        EmployeeViewModel(Repository(RetrofitInstance)).
                        updateEmployee(idTextField.value,Employee(listOf(Data(ageTextField.value!!,nameTextField.value,salaryTextField.value!!,idTextField.value,imageTextField.value!!)),"",""),myContext)

                    },){
                        Text(text = "Update")
                    }
                    Button(

                        onClick = {
                            EmployeeViewModel(Repository(RetrofitInstance)).deleteEmployee(idTextField.value,myContext)

                        }

                    ){
                        Text(text = "Delete")
                    }


                }
            }

        }

    }
}
