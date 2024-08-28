package com.example.newapplication

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.newapplication.model.Data
import com.example.newapplication.model.Employee
import com.example.newapplication.screens.DetailsScreen
import com.example.newapplication.screens.MainScreen
import com.example.newapplication.screens.NewUserScreen
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
                //val navController = rememberNavController()
                FetchEmployees()
                /*
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
                        DetailsScreen(
                            name = it.arguments?.getString("name"),
                            age = it.arguments?.getInt("age"),
                            salary = it.arguments?.getInt("salary"),
                            id = it.arguments?.getInt("id"),
                            image = it.arguments?.getString("image")
                        )
                    }
                    composable(route = "NewUserScreen"){
                        NewUserScreen(navController= navController)
                    }

                }

                 */

            }

        }
    }
}
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
            MainScreen(employeesList = employeesList, navController= navController)
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
