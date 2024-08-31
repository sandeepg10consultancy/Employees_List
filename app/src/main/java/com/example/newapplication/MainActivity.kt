package com.example.newapplication

import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.annotation.RequiresExtension
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.newapplication.model.Data
import com.example.newapplication.screens.DetailsScreen
import com.example.newapplication.screens.MainScreen
import com.example.newapplication.screens.NewUserScreen
import com.example.newapplication.ui.theme.NewApplicationTheme
import com.example.newapplication.utils.RetrofitInstance
import com.example.newapplication.viewModel.EmployeeViewModel
import com.example.newapplication.viewModel.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

class MainActivity : ComponentActivity() {
    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NewApplicationTheme {
                val navController = rememberNavController()
                var employeesList by remember {
                    mutableStateOf(listOf<Data>())
                }
                //FetchEmployees()
                val scope = rememberCoroutineScope()
                LaunchedEffect(key1 = true) {
                    scope.launch(Dispatchers.IO) {
                        employeesList = EmployeeViewModel(Repository(RetrofitInstance)).getEmployee()
                    }
                }

                NavHost(navController = navController, startDestination = "MainScreen") {
                    composable(route = "MainScreen"){
                        MainScreen(employeesList = employeesList, navController = navController)
                    }
                    composable(route = "DetailsScreen?name={name}&age={age}&salary={salary}&id={id}&image={image}", arguments = listOf(
                        navArgument(name = "name"){
                            type = NavType.StringType
                        },
                        navArgument(name = "age"){
                            type = NavType.IntType
                        },
                        navArgument(name = "salary"){
                            type = NavType.IntType
                        },
                        navArgument(name = "id"){
                            type = NavType.IntType
                        },
                        navArgument(name = "image"){
                            type = NavType.StringType
                        }

                    )){
                        it.arguments?.getInt("id")?.let { it1 ->
                            DetailsScreen(
                                name = it.arguments?.getString("name"),
                                age = it.arguments?.getInt("age"),
                                salary = it.arguments?.getInt("salary"),
                                id = it1,
                                image = it.arguments?.getString("image")
                            )
                        }
                    }
                    composable(route = "NewUserScreen"){
                        NewUserScreen(navController= navController)
                    }

                }



            }

        }
    }
}
/*

@Composable
fun FetchEmployees(){
    val navController = rememberNavController()
    var employeesList by remember {
        mutableStateOf(listOf<Data>())
    }
    val myContext = LocalContext.current
    val scope = rememberCoroutineScope()
    LaunchedEffect(key1 = true) {
        scope.launch(Dispatchers.IO){
            val response = try {
                //apiCall()
                RetrofitInstance.api.getEmployee()
            }catch (e:IOException){
                Toast.makeText(myContext,"app error: ${e.message}",Toast.LENGTH_SHORT).show()

                return@launch
            }catch (e:HttpException){
                Toast.makeText(myContext,"Http error: ${e.message}",Toast.LENGTH_SHORT).show()

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
            MainScreen(employeesList = employeesList, navController = navController)
        }
        composable(route = "DetailsScreen?name={name}&age={age}&salary={salary}&id={id}&image={image}", arguments = listOf(
            navArgument(name = "name"){
                type = NavType.StringType
            },
            navArgument(name = "age"){
                type = NavType.IntType
            },
            navArgument(name = "salary"){
                type = NavType.IntType
            },
            navArgument(name = "id"){
                type = NavType.IntType
            },
            navArgument(name = "image"){
                type = NavType.StringType
            }

        )){
            it.arguments?.let { it1 ->
                DetailsScreen(
                    name = it.arguments?.getString("name"),
                    age = it.arguments?.getInt("age"),
                    salary = it.arguments?.getInt("salary"),
                    id = it1.getInt("id"),
                    image = it.arguments?.getString("image")
                )
            }
        }
        composable(route = "NewUserScreen"){
            NewUserScreen(navController= navController)
        }

    }
}


 */