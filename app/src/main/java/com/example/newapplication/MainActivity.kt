package com.example.newapplication

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.newapplication.model.Data
import com.example.newapplication.screens.DetailsScreen
import com.example.newapplication.screens.MainScreen
import com.example.newapplication.ui.theme.NewApplicationTheme
import com.example.newapplication.utils.RetrofitInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NewApplicationTheme {
                val navController = rememberNavController()
                var employeesList by remember {
                    mutableStateOf(listOf<Data>())
                }
                val scope = rememberCoroutineScope()
                LaunchedEffect(key1 = true) {
                    scope.launch(Dispatchers.IO){
                        val response = try {
                            RetrofitInstance.api.getEmployee()
                        }catch (e:IOException){
                            Toast.makeText(this@MainActivity,"app error: ${e.message}",Toast.LENGTH_SHORT).show()

                            return@launch
                        }catch (e:HttpException){
                            Toast.makeText(this@MainActivity,"Http error: ${e.message}",Toast.LENGTH_SHORT).show()

                            return@launch
                        }
                        if(response.isSuccessful && response.body() != null){
                            withContext(Dispatchers.Main){
                                employeesList = response.body()!!.data
                            }

                        }
                    }

                }
                NavHost(navController = navController, startDestination = "MainScreen") {
                    composable(route = "MainScreen"){
                        MainScreen(employeesList = employeesList, navController= navController)
                    }
                    composable(route = "DetailsScreen?name={name}", arguments = listOf(
                        navArgument(name = "name"){
                            type = NavType.StringType
                        }
                    )){
                        DetailsScreen(
                            name = it.arguments?.getString("name")
                        )
                    }

                }

            }
        }
    }
}
