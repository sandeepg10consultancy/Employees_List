package com.example.newapplication.screens

import android.content.Context
import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresExtension
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.newapplication.R
import com.example.newapplication.model.Data
import com.example.newapplication.model.Employee
import com.example.newapplication.utils.RetrofitInstance
import com.example.newapplication.viewModel.EmployeeViewModel
import com.example.newapplication.viewModel.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import retrofit2.Retrofit
import java.io.IOException
import kotlin.Exception

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
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
    val myContext = LocalContext.current

    Column(
        modifier = Modifier
            .padding(20.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center
    ) {
        TextField(
            value = ageTextField.value.toString(),
            onValueChange = {
                ageTextField.value = it.toInt()
            },
            label = { Text(text = "Enter Your Age") },
            modifier = Modifier.width(300.dp),
            colors = TextFieldDefaults.colors(
                focusedTextColor = Color.Black,
                contentColorFor(backgroundColor = Color.Blue),
                focusedLabelColor = Color.Green,
                unfocusedLabelColor = Color.White
            ),
            textStyle = TextStyle.Default.copy(fontSize = 20.sp),
            maxLines = 2
        )
        Spacer(modifier = Modifier.padding(top = 40.dp))
        TextField(
            value = nameTextField.value,
            onValueChange = {
                nameTextField.value = it
            },
            label = { Text(text = "Enter Your Name") },
            modifier = Modifier.width(300.dp),
            colors = TextFieldDefaults.colors(
                focusedTextColor = Color.Black,
                contentColorFor(backgroundColor = Color.Blue),
                focusedLabelColor = Color.Green,
                unfocusedLabelColor = Color.White
            ),
            textStyle = TextStyle.Default.copy(fontSize = 20.sp),
            maxLines = 2
        )
        Spacer(modifier = Modifier.padding(top = 40.dp))
        TextField(
            value = salaryTextField.value.toString(),
            onValueChange = {
                salaryTextField.value = it.toInt()
            },
            label = { Text(text = "Enter Your Salary") },
            modifier = Modifier.width(300.dp),
            colors = TextFieldDefaults.colors(
                focusedTextColor = Color.Black,
                contentColorFor(backgroundColor = Color.Blue),
                focusedLabelColor = Color.Green,
                unfocusedLabelColor = Color.White
            ),
            textStyle = TextStyle.Default.copy(fontSize = 20.sp),
            maxLines = 2
        )
        Spacer(modifier = Modifier.padding(top = 40.dp))
        TextField(
            value = idTextField.value.toString(),
            onValueChange = {
                idTextField.value = it.toInt()
            },
            label = { Text(text = "Enter Your ID") },
            modifier = Modifier.width(300.dp),
            colors = TextFieldDefaults.colors(
                focusedTextColor = Color.Black,
                contentColorFor(backgroundColor = Color.Blue),
                focusedLabelColor = Color.Green,
                unfocusedLabelColor = Color.White
            ),
            textStyle = TextStyle.Default.copy(fontSize = 20.sp),
            maxLines = 2
        )
        Spacer(modifier = Modifier.padding(top = 40.dp))
        TextField(
            value = imageTextField.value,
            onValueChange = {
                imageTextField.value = it
            },
            label = { Text(text = "Enter Profile Image URL") },
            modifier = Modifier.width(300.dp),
            colors = TextFieldDefaults.colors(
                focusedTextColor = Color.Black,
                contentColorFor(backgroundColor = Color.Blue),
                focusedLabelColor = Color.Green,
                unfocusedLabelColor = Color.White
            ),
            textStyle = TextStyle.Default.copy(fontSize = 20.sp),
            maxLines = 2
        )

        Spacer(modifier = Modifier.padding(top = 40.dp))
        Button(onClick = {
            EmployeeViewModel(Repository(RetrofitInstance)).
            createEmployee(Employee(listOf(Data(ageTextField.value,nameTextField.value,salaryTextField.value,idTextField.value,imageTextField.value)),"",""),myContext)

        }) {
            Text(text = "Add")
        }

    }
}
